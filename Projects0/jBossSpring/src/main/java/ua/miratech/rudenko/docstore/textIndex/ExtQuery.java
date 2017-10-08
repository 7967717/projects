package ua.miratech.rudenko.docstore.textIndex;

import java.util.Date;

/**
 * Created by RomanR on 2/28/14.
 */
public class ExtQuery {

    private String author;
    private String subject;
    private Integer idLang;
    private String language;
    private String title;
    private Date createdDate;
    private String created;
    private String createdDay;
    private String createdMonth;
    private String createdYear;
    private Date modifiedDate;
    private String modified;
    private String modifiedDay;
    private String modifiedMonth;
    private String modifiedYear;
    private String paperSize;
    private Integer numberOfPages;
    private String keywords;
    private String text;
    private Integer idArticleType;
    private String type;
    private String path;
    private String userName;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(String paperSize) {
        this.paperSize = paperSize;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getIdLang() {
        return idLang;
    }

    public void setIdLang(Integer idLang) {
        this.idLang = idLang;
    }

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(String createdDay) {
        this.createdDay = createdDay;
    }

    public String getCreatedMonth() {
        return createdMonth;
    }

    public void setCreatedMonth(String createdMonth) {
        this.createdMonth = createdMonth;
    }

    public String getCreatedYear() {
        return createdYear;
    }

    public void setCreatedYear(String createdYear) {
        this.createdYear = createdYear;
    }

    public String getModifiedDay() {
        return modifiedDay;
    }

    public void setModifiedDay(String modifiedDay) {
        this.modifiedDay = modifiedDay;
    }

    public String getModifiedMonth() {
        return modifiedMonth;
    }

    public void setModifiedMonth(String modifiedMonth) {
        this.modifiedMonth = modifiedMonth;
    }

    public String getModifiedYear() {
        return modifiedYear;
    }

    public void setModifiedYear(String modifiedYear) {
        this.modifiedYear = modifiedYear;
    }

}
