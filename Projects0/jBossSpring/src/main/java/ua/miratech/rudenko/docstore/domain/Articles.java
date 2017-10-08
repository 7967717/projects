package ua.miratech.rudenko.docstore.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by RomanR on 1/30/14.
 */
public class Articles implements Serializable {

    private Integer id;
    private String author;
    private String title;
    private Date created;
    private Integer idOwner;
    private String path;
    private String md5;
    private String status;
    private Integer sharedType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSharedType() {
        return sharedType;
    }

    public void setSharedType(Integer sharedType) {
        this.sharedType = sharedType;
    }
}
