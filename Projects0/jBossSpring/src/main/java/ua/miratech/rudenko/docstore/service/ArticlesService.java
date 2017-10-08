package ua.miratech.rudenko.docstore.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.miratech.rudenko.docstore.configuration.ConfigurationManager;
import ua.miratech.rudenko.docstore.domain.*;
import ua.miratech.rudenko.docstore.persistence.ArticlesMapper;
import ua.miratech.rudenko.docstore.textIndex.ExtQuery;

import java.util.*;

/**
 * Created by RomanR on 1/28/14.
 */

@Service
public class ArticlesService {

    public static final Logger LOG = Logger.getLogger("rootLogger");

    @Autowired
    ArticlesMapper articlesMapper;
    @Autowired
    ConfigurationManager configurationManager;

    public ArrayList<Articles> getArticlesByUser(String idOwner) {
        return (ArrayList<Articles>) articlesMapper.getArticlesByUser(idOwner);
    }

    public ArrayList<FoundArticle> getFoundArticles(List<String> foundPathList) {
        ArrayList<FoundArticle> foundArticles = new ArrayList<FoundArticle>();
        for (String path : foundPathList) {
            foundArticles.addAll(articlesMapper.getArticleByFoundPath(path));
        }
        for (FoundArticle article : foundArticles) {
            String path = article.getPath();
            path = path.replace(configurationManager.getProperty("MAIN_PATH"), "");
            path = path.replace(configurationManager.getProperty("EXT"), "");
            LOG.info("path " + path);
            if (article.getId().equals(Integer.valueOf(path))) {
                article.setDuplicate(configurationManager.getProperty("ORIGINAL"));
            } else {
                article.setDuplicate(configurationManager.getProperty("DUPLICATE"));
            }
        }
        return foundArticles;
    }

    public Set<FoundArticle> getFoundArticlesWithParameters(List<String> foundPathList, String userName) {
        Set<FoundArticle> foundArticles = new HashSet<FoundArticle>();
        for (String path : foundPathList) {
            UserAndPath userAndPath = new UserAndPath();
            userAndPath.setPath(path);
            userAndPath.setUserName(userName);
            foundArticles.addAll(articlesMapper.getArticleByFoundPathAndUser(userAndPath));
        }
        for (FoundArticle article : foundArticles) {
            String path = article.getPath();
            path = path.replace(configurationManager.getProperty("MAIN_PATH"), "");
            path = path.replace(configurationManager.getProperty("EXT"), "");
            LOG.info("path " + path);
            if (article.getId().equals(Integer.valueOf(path))) {
                article.setDuplicate(configurationManager.getProperty("ORIGINAL"));
            } else {
                article.setDuplicate(configurationManager.getProperty("DUPLICATE"));
            }
        }
        return foundArticles;
    }

    public Articles getArticleById(Integer id) {
        return articlesMapper.getArticleById(id);
    }

    public NewArticle getNewArticleByID(String id) {
        return articlesMapper.getNewArticleByID(id);
    }

    public Integer selectNewArticleId() {
        return articlesMapper.selectNewArticleId();
    }

    public void insertNewArticle(NewArticle newArticle) {
        articlesMapper.insertNewArticle(newArticle);
    }

    public void updateNewArticle(NewArticle newArticle) {
        articlesMapper.updateNewArticle(newArticle);
    }

    public String getFileNameByPath(String path) {
        List<String> fileNames = articlesMapper.getFileNameByPath(path);
        return fileNames.get(0);
    }

    public ArrayList<Articles> getArticlesMd5() {
        return (ArrayList<Articles>) articlesMapper.getArticlesMd5();
    }

    public void updateNewArticleStatus(ArticleStatus article) {
        articlesMapper.updateNewArticleStatus(article);
    }

    public Integer getSharedType(Integer id) {
        return articlesMapper.getSharedType(id);
    }

    public ArrayList<String> getSharedTypeList() {
        return (ArrayList<String>) articlesMapper.getSharedTypeList();
    }

    public LinkedHashMap<Integer, String> getSharedTypeMap() {
        ArrayList<SharedType> sharedTypes = (ArrayList<SharedType>) articlesMapper.getSharedTypeMap();
        LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
        for (SharedType sharedType : sharedTypes) {
            map.put(sharedType.getIdSharedType(), sharedType.getSharedType());
        }
        return map;
    }

    public LinkedHashMap<Integer, String> getLanguagesMap() {
        ArrayList<Languages> languages = (ArrayList<Languages>) articlesMapper.getLanguagesMap();
        LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
        for (Languages language : languages) {
            map.put(language.getIdLang(), language.getLanguage());
        }
        return map;
    }

    public LinkedHashMap<Integer, String> getArticleTypeMap() {
        ArrayList<ArticleType> articleTypes = (ArrayList<ArticleType>) articlesMapper.getArticleTypeMap();
        LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
        for (ArticleType articleType : articleTypes) {
            map.put(articleType.getIdArticleType(), articleType.getType());
        }
        return map;
    }

    public Set<FoundArticle> getArticleByExtQuery(List<String> foundPathList, String userName, ExtQuery extQuery) {
        LOG.info("entered getArticleByExtQuery ");
        Set<FoundArticle> foundArticles = new HashSet<FoundArticle>();
        if (foundPathList.isEmpty()) {
            extQuery.setUserName(userName);
            LOG.info("foundArticles1 " + foundArticles);
            foundArticles.addAll(articlesMapper.getArticleByExtQuery(extQuery));
            LOG.info("foundArticles2 " + foundArticles);
        } else {
            for (String path : foundPathList) {
                extQuery.setPath(path);
                extQuery.setUserName(userName);
                LOG.info("NumberOfPages " + extQuery.getNumberOfPages());
                LOG.info("IdLang " + extQuery.getIdLang());
                foundArticles.addAll(articlesMapper.getArticleByExtQuery(extQuery));
            }
        }
        for (FoundArticle article : foundArticles) {
            String path = article.getPath();
            path = path.replace(configurationManager.getProperty("MAIN_PATH"), "");
            path = path.replace(configurationManager.getProperty("EXT"), "");
            LOG.info("path " + path);
            if (article.getId().equals(Integer.valueOf(path))) {
                article.setDuplicate(configurationManager.getProperty("ORIGINAL"));
            } else {
                article.setDuplicate(configurationManager.getProperty("DUPLICATE"));
            }
        }
        return foundArticles;
    }

}
