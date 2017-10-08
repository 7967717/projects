package ua.miratech.rudenko.docstore.domain;

import java.io.Serializable;

/**
 * Created by RomanR on 2/19/14.
 */
public class Shared implements Serializable {
    private Integer idShared;
    private Integer id;
    private String owner;
    private String grantee;

    public Integer getIdShared() {
        return idShared;
    }

    public void setIdShared(Integer idShared) {
        this.idShared = idShared;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getGrantee() {
        return grantee;
    }

    public void setGrantee(String grantee) {
        this.grantee = grantee;
    }
}
