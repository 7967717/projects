/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.beans;

import java.io.Serializable;
import java.sql.Date;


/**
 * @author romanrudenko
 */
public class OrderTrans implements Serializable  {
    private int idOrder;
    private int transTypeId;
    private Date date;
    private long sum;
    private boolean confirmed;
    private int accountId;
    private int userId;

    public OrderTrans() {
    }

    public OrderTrans(int idOrder) {
        this.idOrder = idOrder;
    }

    public OrderTrans(int transTypeId, long sum, int accountId, int userId) {
        this.transTypeId = transTypeId;
        this.sum = sum;
        this.accountId = accountId;
        this.userId = userId;
    }
    
    

    public OrderTrans(int idOrder, int transTypeId, Date date, long sum, boolean confirmed,
                      int accountId, int userId) {
        this.idOrder = idOrder;
        this.transTypeId = transTypeId;
        this.date = date;
        this.sum = sum;
        this.confirmed = confirmed;
        this.accountId = accountId;
        this.userId = userId;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getTransTypeId() {
        return transTypeId;
    }

    public void setTransTypeId(int transTypeId) {
        this.transTypeId = transTypeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
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

        OrderTrans that = (OrderTrans) o;

        if (accountId != that.accountId) return false;
        if (confirmed != that.confirmed) return false;
        if (idOrder != that.idOrder) return false;
        if (sum != that.sum) return false;
        if (transTypeId != that.transTypeId) return false;
        if (userId != that.userId) return false;
        if (!date.equals(that.date)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrder;
        result = 31 * result + transTypeId;
        result = 31 * result + date.hashCode();
        result = 31 * result + (int) (sum ^ (sum >>> 32));
        result = 31 * result + (confirmed ? 1 : 0);
        result = 31 * result + accountId;
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "OrderTrans{" +
                "idOrder=" + idOrder +
                ", transTypeId=" + transTypeId +
                ", date=" + date +
                ", sum=" + sum +
                ", confirmed=" + confirmed +
                ", accountId=" + accountId +
                ", userId=" + userId +
                '}';
    }
}
