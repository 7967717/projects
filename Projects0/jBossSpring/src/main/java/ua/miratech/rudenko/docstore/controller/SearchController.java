package ua.miratech.rudenko.docstore.controller;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.servlet.ModelAndView;
import ua.miratech.rudenko.docstore.configuration.ConfigurationManager;
import ua.miratech.rudenko.docstore.domain.ArticleType;
import ua.miratech.rudenko.docstore.domain.Articles;
import ua.miratech.rudenko.docstore.domain.Languages;
import ua.miratech.rudenko.docstore.domain.Users;
import ua.miratech.rudenko.docstore.service.ArticlesService;
import ua.miratech.rudenko.docstore.service.SharedService;
import ua.miratech.rudenko.docstore.service.UsersService;
import ua.miratech.rudenko.docstore.textIndex.ExtQuery;
import ua.miratech.rudenko.docstore.textIndex.Query;
import ua.miratech.rudenko.docstore.textIndex.SearchQuery;
import ua.miratech.rudenko.docstore.validators.ExtQueryValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by RomanR on 2/13/14.
 */
@Controller
@Scope("session")
public class SearchController {

    public static final Logger LOG = Logger.getLogger("rootLogger");

    @Autowired
    ArticlesService articlesService;
    @Autowired
    SearchQuery searchQuery;
    @Autowired
    SharedService sharedService;
    @Autowired
    UsersService usersService;
    @Autowired
    ExtQueryValidator extQueryValidator;
    @Autowired
    ConfigurationManager configurationManager;


    private Users user = new Users();
    LinkedHashMap<Integer, String> languagesMap;
    LinkedHashMap<Integer, String> articleTypeMap;

    @RequestMapping(value = "user/userProfile")
    public ModelAndView gotoUserProfile(HttpServletRequest request) {
        LOG.info("entered userProfile page");
        saveUserIdToSession(request);
        user = (Users) request.getSession().getAttribute("user");
        String message = "Hello " + user.getUsername() + "!";
        return new ModelAndView("user/userProfile", "message", message);
    }

    @RequestMapping(value = "user/search")
    public ModelAndView gotoSearch(HttpServletRequest request) {
        LOG.info("entered  Search page");

        saveUserIdToSession(request);
        LOG.info("Login in user " + user.getUsername());

        return new ModelAndView("user/search", "query", new Query());
    }

    @RequestMapping(value = "user/submitSearch", method = RequestMethod.POST)
    public ModelAndView submitSearch(@ModelAttribute("query") Query query, ModelMap model,
                                     HttpServletRequest request) throws UnsupportedEncodingException {

        LOG.info("submitted query");
        LOG.info(query.getQuery());

        if (query.getQuery().isEmpty()) {
            LOG.info("empty query");
            return new ModelAndView("user/search", "query", new Query());

        } else {
            LOG.info("started search");

            // search ALL AVAILABLE ARTICLES
//            model.addAttribute("articles", articlesService.getFoundArticles
//                    (searchQuery.foundPathList(INDEX_PATH, query.getQuery(), MAX_HITS)));

            // search articles the user has access ONLY
            user = (Users) request.getSession().getAttribute("user");

            model.addAttribute("articles", articlesService.getFoundArticlesWithParameters
                    (searchQuery.foundPathList(configurationManager.getProperty("INDEX_PATH"), query.getQuery(),
                            Integer.valueOf(configurationManager.getProperty("MAX_HITS"))), user.getUsername()));
            LOG.info("show found articles");
            return new ModelAndView("user/search");
        }

    }

    @RequestMapping(value = "user/extSearch")
    public ModelAndView gotoExtSearch(ModelMap model, HttpServletRequest request) {
        LOG.info("entered  extSearch page");

        saveUserIdToSession(request);

        Languages languages = new Languages();
        ArticleType articleType = new ArticleType();
        languagesMap = articlesService.getLanguagesMap();
        articleTypeMap = articlesService.getArticleTypeMap();
        model.addAttribute("languagesMap", languagesMap);
        model.addAttribute("articleTypeMap", articleTypeMap);
        model.addAttribute("languages", languages);
        model.addAttribute("articleType", articleType);

        return new ModelAndView("user/extSearch", "extQuery", new ExtQuery());
    }

    @RequestMapping(value = "user/submitExtSearch", method = RequestMethod.POST)
    public ModelAndView submitExtSearch(@ModelAttribute("extQuery") ExtQuery query, BindingResult result,
                                        ModelMap model, HttpServletRequest request) {

        LOG.info("submitted query");

        model.addAttribute("languagesMap", languagesMap);
        model.addAttribute("articleTypeMap", articleTypeMap);

        if (query.getAuthor().isEmpty() && query.getSubject().isEmpty() && query.getIdLang() == null
                && query.getTitle().isEmpty()
                && query.getCreatedDay().isEmpty() && query.getCreatedMonth().isEmpty() && query.getCreatedYear().isEmpty()
                && query.getModifiedDay().isEmpty() && query.getModifiedMonth().isEmpty() && query.getModifiedYear().isEmpty()
                && query.getPaperSize().isEmpty() && query.getNumberOfPages() == null
                && query.getKeywords().isEmpty() && query.getText().isEmpty()
                && query.getIdArticleType() == null) {
            LOG.info("empty query");
            return new ModelAndView("user/extSearch");

        }

        if (!query.getCreatedDay().isEmpty() || !query.getModifiedDay().isEmpty()) { //TODO doesn't work validator for modified
//            extQueryValidator.validate(query, result);
//            LOG.info("goto validator");
//            if (result.hasErrors()) {
//                LOG.info("form has Errors");
//                return new ModelAndView("user/extSearch");
//            }
//        } else {
            query.setCreatedDate(parseDate(query.getCreatedDay(), query.getCreatedMonth(),
                    query.getCreatedYear()));
            query.setModifiedDate(parseDate(query.getModifiedDay(), query.getModifiedMonth(),
                    query.getModifiedYear()));
        }

        LOG.info("started search");
        DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        LOG.info("pages " + query.getNumberOfPages());
//        LOG.info("CreatedDate " + sdf.format(query.getCreatedDate()));

        user = (Users) request.getSession().getAttribute("user");
        model.addAttribute("articles", articlesService.getArticleByExtQuery
                (searchQuery.extFoundPathList(configurationManager.getProperty("INDEX_PATH"), query.getAuthor(),
                        query.getSubject(), query.getTitle(), query.getKeywords(), query.getText(),
                        Integer.valueOf(configurationManager.getProperty("MAX_HITS"))), user.getUsername(), query));

        LOG.info("show found articles");
        return new ModelAndView("user/extSearch");

    }

    @RequestMapping("user/readArticle")
    public void goToReadArticle(@ModelAttribute("Articles") Articles articles, ModelMap model,
                                HttpServletResponse response, HttpServletRequest request) {

        LOG.info("entered page readArticle");
        user = (Users) request.getSession().getAttribute("user");
        articles = articlesService.getArticleById(articles.getId());

        String fileURL = articles.getPath();
        File fileToRead = new File(fileURL);
        FileInputStream inputStream = null;

        try {
            // gets file as InputStream
            inputStream = new FileInputStream(fileToRead);
        } catch (FileNotFoundException e) {
            LOG.info(e);
            e.printStackTrace();
        }

        response.setContentType("application/pdf");

        try {
            // copy it to response's OutputStream
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            LOG.info("Error writing file to output stream. Filename was '" + fileToRead + "'");
        }

        // this could be used to check read access when user search ALL AVAILABLE ARTICLES

//        if (articlesService.getSharedType(articles.getId()).equals(SHARED_TYPE_PUBLIC) ||
//                sharedService.checkAccessRights(articles, user.getUsername()) == true) {
//
//            String fileURL = articles.getPath();
//            File fileToRead = new File(fileURL);
//            FileInputStream inputStream = null;
//
//            try {
//                // gets file as InputStream
//                inputStream = new FileInputStream(fileToRead);
//            } catch (FileNotFoundException e) {
//                LOG.info(e);
//                e.printStackTrace();
//            }
//
//            response.setContentType("application/pdf");
//
//            try {
//                // copy it to response's OutputStream
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//            } catch (IOException ex) {
//                LOG.info("Error writing file to output stream. Filename was '" + fileToRead + "'");
//            }
//            return new ModelAndView("user/readArticle");
//        }
//        Shared shared = new Shared();
//        shared.setId(articles.getId());
//        model.addAttribute("Shared", shared);
//        return new ModelAndView("user/requestAccess");
    }

    @RequestMapping("user/downloadArticle")
    public void downloadArticle(@ModelAttribute("Articles") Articles articles, ModelMap model,
                                HttpServletResponse response, HttpServletRequest request) {

        LOG.info("entered page downloadArticle");
        user = (Users) request.getSession().getAttribute("user");
        articles = articlesService.getArticleById(articles.getId());

        String fileURL = articles.getPath();
        String fileName = articlesService.getFileNameByPath(fileURL);

        File fileToDownload = new File(fileURL);
        FileInputStream inputStream = null;

        try {
            // gets file as InputStream
            inputStream = new FileInputStream(fileToDownload);
        } catch (FileNotFoundException e) {
            LOG.info(e);
            e.printStackTrace();
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        try {
            // copy it to response's OutputStream
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            LOG.info("Error writing file to output stream. Filename was '" + fileToDownload + "'");
        }

        // this could be used to check download access when user search ALL AVAILABLE ARTICLES

//        if (articlesService.getSharedType(articles.getId()).equals(SHARED_TYPE_PUBLIC) ||
//                sharedService.checkAccessRights(articles, user.getUsername()) == true) {
//
//            String fileURL = articles.getPath();
//            String fileName = articlesService.getFileNameByPath(fileURL);
//
//            File fileToDownload = new File(fileURL);
//            FileInputStream inputStream = null;
//
//            try {
//                // gets file as InputStream
//                inputStream = new FileInputStream(fileToDownload);
//            } catch (FileNotFoundException e) {
//                LOG.info(e);
//                e.printStackTrace();
//            }
//
//            response.setContentType("application/pdf");
//            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//
//            try {
//                // copy it to response's OutputStream
//                IOUtils.copy(inputStream, response.getOutputStream());
//                response.flushBuffer();
//            } catch (IOException ex) {
//                LOG.info("Error writing file to output stream. Filename was '" + fileToDownload + "'");
//            }
//            return new ModelAndView("user/readArticle");
//        }
//
//        Shared shared = new Shared();
//        shared.setId(articles.getId());
//        model.addAttribute("Shared", shared);
//        return new ModelAndView("user/requestAccess");

    }

    private void saveUserIdToSession(HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        user.setUsername(name);
        user.setUserId(usersService.getIdByName(name));
        request.getSession().setAttribute("user", user);
    }


    private Date parseDate(String date){
        Date d = new Date();
        DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            LOG.info("I could not parse the date " + date);
        }
        return d;
    }

    private Date parseDate(String day, String month, String year){
        Date d = new Date();
        String date;
        date = new StringBuilder().append(day).append("/").append(month).append("/").append(year).toString();
        LOG.info("string date " + date);
        DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            LOG.info("I could not parse the date " + date);
        }
        return d;
    }

}
