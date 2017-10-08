package ua.miratech.rudenko.docstore.persistence;

import org.springframework.stereotype.Component;
import ua.miratech.rudenko.docstore.domain.*;
import ua.miratech.rudenko.docstore.textIndex.ExtQuery;

import java.util.List;

/**
 * Created by RomanR on 1/28/14.
 */
@Component
public interface ArticlesMapper {

    List<Articles> getArticlesByUser (String idOwner);

    List<FoundArticle> getArticleByFoundPath(String path);

    List<FoundArticle> getArticleByFoundPathAndUser(UserAndPath userAndPath);

    List<FoundArticle> getArticleByExtQuery(ExtQuery extQuery);

    Articles getArticleById(Integer id);

    NewArticle getNewArticleByID(String id);

    Integer selectNewArticleId ();

    void insertNewArticle (NewArticle newArticle);

    void updateNewArticle (NewArticle newArticle);

    List<String> getFileNameByPath (String path);

    List<Articles> getArticlesMd5 ();

    void updateNewArticleStatus (ArticleStatus article);

    Integer getSharedType(Integer id);

    List<String> getSharedTypeList ();

    List<SharedType> getSharedTypeMap();

    List<Languages> getLanguagesMap();

    List<ArticleType> getArticleTypeMap();

}
