package ua.miratech.rudenko.docstore.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.miratech.rudenko.docstore.configuration.ConfigurationManager;
import ua.miratech.rudenko.docstore.domain.ArticleStatus;
import ua.miratech.rudenko.docstore.domain.Articles;
import ua.miratech.rudenko.docstore.domain.NewArticle;
import ua.miratech.rudenko.docstore.domain.Users;
import ua.miratech.rudenko.docstore.fileUploadDownload.UploadedFile;
import ua.miratech.rudenko.docstore.jobsExecution.JobsThreadPool;
import ua.miratech.rudenko.docstore.pdfParser.ReadPdf;
import ua.miratech.rudenko.docstore.service.ArticlesService;
import ua.miratech.rudenko.docstore.service.UsersService;
import ua.miratech.rudenko.docstore.textIndex.IndexFiles;
import ua.miratech.rudenko.docstore.validators.FileUploadValidator;
import ua.miratech.rudenko.docstore.validators.NewArticleValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by RomanR on 1/28/14.
 */
@Controller
@Scope("session")
public class ArticlesController {

    public static final Logger LOG = Logger.getLogger("rootLogger");

    @Autowired
    FileUploadValidator fileValidator;
    @Autowired
    NewArticleValidator newArticleValidator;
    @Autowired
    ArticlesService articlesService;
    @Autowired
    UsersService usersService;
    @Autowired
    ReadPdf readPdf;
    @Autowired
    JobsThreadPool jobsThreadPool;
    @Autowired
    ConfigurationManager configurationManager;

    private Users user = new Users();

    @RequestMapping(value = {"user/addArticle", "user/submitEditMeta"})
    public ModelAndView gotoAddArticle1(ModelMap model, HttpServletRequest request) {
        LOG.info("entered page addArticle");

        saveUserIdToSession(request);
        user = (Users) request.getSession().getAttribute("user");
        model.addAttribute("articles", articlesService.getArticlesByUser(user.getUsername()));

        return new ModelAndView("user/addArticle");
    }


    /**
     * uploadFile file
     */
    @RequestMapping(value = "user/addArticle", method = RequestMethod.POST)
    public ModelAndView uploadFile(@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
                                   BindingResult result,
                                   ModelMap model,
                                   NewArticle newArticle) {

        LOG.info("entered uploadFile");

        LOG.info("goto validator");
        fileValidator.validate(uploadedFile, result);
        if (result.hasErrors()) {
            model.addAttribute("articles", articlesService.getArticlesByUser(user.getUsername()));
            return new ModelAndView("user/addArticle");
        }

        MultipartFile file = uploadedFile.getFile();
        InputStream inputStream = null;
        String originalFilename = file.getOriginalFilename();
        try {

            Long size = file.getSize();
            Integer fileSize = Integer.valueOf(size.intValue());
            Integer id = articlesService.selectNewArticleId();
            String fileName = id + configurationManager.getProperty("FILE_TYPE");

            LOG.info("got file - " + originalFilename);
            LOG.info("file size - " + fileSize);

            String md5 = uploadedFile.generateBufferedHash(file.getInputStream());
            LOG.info("file MD5 code " + md5);
            String path = configurationManager.getProperty("PATH") + "/" + fileName;
            String newPath = checkFileDuplicate(md5, path);

            if (newPath.equals(path)) {
                inputStream = file.getInputStream();
                LOG.info("go to save file");
                uploadedFile.saveUploadedFile(inputStream, configurationManager.getProperty("PATH"), fileName);
            } else {
                LOG.info("this is a duplicate, saving in database only!!! ");
            }

            readPdf.init(file.getInputStream());

            newArticle.setId(id);
            newArticle.setAuthor(readPdf.getMetaInfo("Author"));
            newArticle.setSubject(readPdf.getMetaInfo("Subject"));
            newArticle.setStatus(configurationManager.getProperty("ARTICLE_INDEX_STATUS_N"));
            newArticle.setTitle(readPdf.getMetaInfo("Title"));
            newArticle.setFileName(originalFilename);
            newArticle.setFileSize(fileSize);
            newArticle.setPath(newPath);
            newArticle.setMd5(md5);
            newArticle.setIdOwner(user.getUserId());
            newArticle.setKeywords(readPdf.getMetaInfo("Keywords"));
            newArticle.setPageSize(readPdf.getPageSize());
            newArticle.setNumberOfPages(readPdf.getPagesN());
            newArticle.setFileType(configurationManager.getProperty("FILE_TYPE"));

            articlesService.insertNewArticle(newArticle);
            LOG.info("saved article with ID = " + newArticle.getId());
            model.addAttribute(newArticle);
            LinkedHashMap<Integer, String> sharedTypeMap = articlesService.getSharedTypeMap();
            LinkedHashMap<Integer, String> languagesMap = articlesService.getLanguagesMap();
            LinkedHashMap<Integer, String> articleTypeMap = articlesService.getArticleTypeMap();
            model.addAttribute("sharedTypeMap", sharedTypeMap);
            model.addAttribute("languagesMap", languagesMap);
            model.addAttribute("articleTypeMap", articleTypeMap);


            if (newPath.equals(path)) {
                LOG.info("go to indexing");
                boolean newIndex = Boolean.valueOf(configurationManager.getProperty("NEW_INDEX"));
                jobsThreadPool.getThreadPool().execute(
                        new IndexFiles(newArticle.getPath(), configurationManager.getProperty("INDEX_PATH"),
                                newIndex, newArticle.getId(), articlesService));
            } else {
                LOG.info("go to change status");
                ArticleStatus article = new ArticleStatus();
                article.setId(newArticle.getId());
                article.setStatus(configurationManager.getProperty("ARTICLE_INDEX_STATUS_Y"));
                LOG.info("id " + article.getId());
                LOG.info("status " + article.getStatus());
                articlesService.updateNewArticleStatus(article);
            }

        } catch (IOException e) {
            LOG.error("could not process the file - " + originalFilename);
            e.printStackTrace();

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new ModelAndView("user/editMeta");
    }

    @RequestMapping("user/viewMeta")
    public ModelAndView gotoViewMetaForm(@ModelAttribute("Articles") Articles articles, ModelMap model) {

        LOG.info("entered page ViewMeta");

        NewArticle newArticle = articlesService.getNewArticleByID(String.valueOf(articles.getId()));
        model.addAttribute(newArticle);

        return new ModelAndView("user/viewMeta");
    }

    @RequestMapping("user/editMeta")
    public ModelAndView gotoEditMetaForm(@ModelAttribute("Articles") Articles articles, ModelMap model) {

        LOG.info("entered page EditMeta");

        LinkedHashMap<Integer, String> sharedTypeMap = articlesService.getSharedTypeMap();
        LinkedHashMap<Integer, String> languagesMap = articlesService.getLanguagesMap();
        LinkedHashMap<Integer, String> articleTypeMap = articlesService.getArticleTypeMap();
        model.addAttribute("sharedTypeMap", sharedTypeMap);
        model.addAttribute("languagesMap", languagesMap);
        model.addAttribute("articleTypeMap", articleTypeMap);
        NewArticle newArticle = articlesService.getNewArticleByID(String.valueOf(articles.getId()));
        model.addAttribute(newArticle);

        return new ModelAndView("user/editMeta");
    }

    @RequestMapping(value = "user/submitEditMeta", method = RequestMethod.POST)
    public ModelAndView submitEditMeta(@ModelAttribute("newArticle") NewArticle newArticle, BindingResult result,
                                       HttpServletRequest request, ModelMap model) {

        LOG.info("entered page submitEditMeta");

        LOG.info("goto validator");
        newArticleValidator.validate(newArticle, result);
        if (result.hasErrors()) {
            LinkedHashMap<Integer, String> sharedTypeMap = articlesService.getSharedTypeMap();
            LinkedHashMap<Integer, String> languagesMap = articlesService.getLanguagesMap();
            LinkedHashMap<Integer, String> articleTypeMap = articlesService.getArticleTypeMap();
            model.addAttribute("sharedTypeMap", sharedTypeMap);
            model.addAttribute("languagesMap", languagesMap);
            model.addAttribute("articleTypeMap", articleTypeMap);
            return new ModelAndView("user/editMeta");
        }

        saveUserIdToSession(request);

        articlesService.updateNewArticle(newArticle); // TODO add modified updated during update
                                                      // TODO update index after article update

        LOG.info("updated article");

        model.addAttribute("articles", articlesService.getArticlesByUser(user.getUsername()));

        LOG.info("entered article list");

        return new ModelAndView("user/addArticle");
    }

    @RequestMapping("user/confirmDelete")
    public ModelAndView goToConfirmDelete(@ModelAttribute("Articles") Articles articles, ModelMap model) {

        LOG.info("entered page confirmDelete");
        model.addAttribute("id", articles.getId());

        return new ModelAndView("user/confirmDelete");
    }


    private void saveUserIdToSession(HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        user.setUsername(name);
        user.setUserId(usersService.getIdByName(name));
        request.getSession().setAttribute("user", user);
    }

    private String checkFileDuplicate(String md5, String path) {
        ArrayList<Articles> listMd5 = articlesService.getArticlesMd5();
        String newPath = path;
        for (Articles article : listMd5) {
            if (md5.equals(article.getMd5())) {
                newPath = article.getPath();
            }
        }
        return newPath;
    }


}
