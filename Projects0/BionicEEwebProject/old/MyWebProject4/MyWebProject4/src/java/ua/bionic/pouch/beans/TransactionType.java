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
public class TransactionType implements Serializable {
    private int idTransType;
    private String transDesc;

    public TransactionType() {
    }

    public TransactionType(int idTransType) {
        this.idTransType = idTransType;
    }

    public TransactionType(int idTransType, String transDesc) {
        this.idTransType = idTransType;
        this.transDesc = transDesc;
    }

    public int getIdTransType() {
        return idTransType;
    }

    public void setIdTransType(int idTransType) {
        this.idTransType = idTransType;
    }

    public String getTransDesc() {
        return transDesc;
    }

    public void setTransDesc(String transDesc) {
        this.transDesc = transDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionType that = (TransactionType) o;

        if (idTransType != that.idTransType) return false;
        if (!transDesc.equals(that.transDesc)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTransType;
        result = 31 * result + transDesc.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "idTransType=" + idTransType +
                ", transDesc='" + transDesc + '\'' +
                '}';
    }
    
}
