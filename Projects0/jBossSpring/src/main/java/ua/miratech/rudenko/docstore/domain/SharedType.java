package ua.miratech.rudenko.docstore.domain;

import java.io.Serializable;

/**
 * Created by RomanR on 2/26/14.
 */
public class SharedType implements Serializable {
    private Integer idSharedType;
    private String sharedType;

    public Integer getIdSharedType() {
        return idSharedType;
    }

    public void setIdSharedType(Integer idSharedType) {
        this.idSharedType = idSharedType;
    }

    public String getSharedType() {
        return sharedType;
    }

    public void setSharedType(String sharedType) {
        this.sharedType = sharedType;
    }
}
