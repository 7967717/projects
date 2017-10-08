package ua.miratech.rudenko.docstore.domain;

import java.io.Serializable;

/**
 * Created by RomanR on 2/26/14.
 */
public class Languages implements Serializable {
    private Integer idLang;
    private String language;

    public Integer getIdLang() {
        return idLang;
    }

    public void setIdLang(Integer idLang) {
        this.idLang = idLang;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
