/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.beans;

import java.io.Serializable;
import java.sql.Date;


/**
 *
 * @author romanrudenko
 */
public class TransactionHistory implements Serializable {
    
    private int idTrans;
    private int orderId;
    private Date date;
    private int accountId;
    private int userId;

    public TransactionHistory() {
    }

    public TransactionHistory(int idTrans) {
        this.idTrans = idTrans;
    }

    public TransactionHistory(int orderId, int accountId, int userId) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.userId = userId;
    }

    public TransactionHistory(int idTrans, int orderId, Date date, int accountId,
                              int userId) {
        this.idTrans = idTrans;
        this.orderId = orderId;
        this.date = date;
        this.accountId = accountId;
        this.userId = userId;
    }

    public int getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(int idTrans) {
        this.idTrans = idTrans;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionHistory that = (TransactionHistory) o;

        if (accountId != that.accountId) return false;
        if (idTrans != that.idTrans) return false;
        if (orderId != that.orderId) return false;
        if (userId != that.userId) return false;
        if (!date.equals(that.date)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTrans;
        result = 31 * result + orderId;
        result = 31 * result + date.hashCode();
        result = 31 * result + accountId;
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "idTrans=" + idTrans +
                ", orderId=" + orderId +
                ", date=" + date +
                ", accountId=" + accountId +
                ", userId=" + userId +
                '}';
    }
}
