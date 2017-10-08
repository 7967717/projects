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
public class Currency implements Serializable  {
    private int idCurrency;
    private String currencyType;

    public Currency() {
    }

    public Currency(int idCurrency) {
        this.idCurrency = idCurrency;
    }

    public Currency(int idCurrency, String currencyType) {
        this.idCurrency = idCurrency;
        this.currencyType = currencyType;
    }

    public int getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(int idCurrency) {
        this.idCurrency = idCurrency;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (idCurrency != currency.idCurrency) return false;
        if (!currencyType.equals(currency.currencyType)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCurrency;
        result = 31 * result + currencyType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Currency# " + idCurrency + ", Type=" + currencyType + '.';
    }
    
    
    
}
