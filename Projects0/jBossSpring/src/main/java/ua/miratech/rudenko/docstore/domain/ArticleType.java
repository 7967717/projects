package ua.miratech.rudenko.docstore.domain;

import java.io.Serializable;

/**
 * Created by RomanR on 2/26/14.
 */
public class ArticleType implements Serializable {
    private Integer idArticleType;
    private String type;

    public Integer getIdArticleType() {
        return idArticleType;
    }

    public void setIdArticleType(Integer idArticleType) {
        this.idArticleType = idArticleType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
