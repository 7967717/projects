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
public class Account implements Serializable {
    private int idAccount;
    private int currencyId;
    private long balance;
    private int userId;

    public Account() {
    }

    public Account(int idAccount) {
        this.idAccount = idAccount;
    }

    public Account(int idAccount, int currencyId, long balance, int userId) {
        this.idAccount = idAccount;
        this.currencyId = currencyId;
        this.balance = balance;
        this.userId = userId;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
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

        Account account = (Account) o;

        if (balance != account.balance) return false;
        if (currencyId != account.currencyId) return false;
        if (idAccount != account.idAccount) return false;
        if (userId != account.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAccount;
        result = 31 * result + currencyId;
        result = 31 * result + (int) (balance ^ (balance >>> 32));
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return  "Account# " + idAccount +
                ", currencyId - " + currencyId +
                ", balance - " + balance +
//                ", userId=" + userId +
                '.';
    }
    
}
