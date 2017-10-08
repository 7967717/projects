/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.beans;

import java.io.Serializable;

/**
 *
 * @author romanrudenko
 */
public class UserType implements Serializable {
    private int idUserType;
    private String userDesc;

    public UserType() {
    }

    public UserType(int idUserType) {
        this.idUserType = idUserType;
    }

    public UserType(int idUserType, String userDesc) {
        this.idUserType = idUserType;
        this.userDesc = userDesc;
    }

    public int getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(int idUserType) {
        this.idUserType = idUserType;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserType userType = (UserType) o;

        if (idUserType != userType.idUserType) return false;
        if (!userDesc.equals(userType.userDesc)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserType;
        result = 31 * result + userDesc.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "idUserType=" + idUserType +
                ", userDesc='" + userDesc + '\'' +
                '}';
    }
    
}
