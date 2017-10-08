/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.managed.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ua.bionic.pouch.entities.Currencies;
import ua.bionic.pouch.entities.OrderTypes;
import ua.bionic.pouch.entities.Users;
import ua.bionic.pouch.session.beans.currencies.CurrenciesFacadeLocal;
import ua.bionic.pouch.session.beans.orderTypes.OrderTypesFacadeLocal;
import ua.bionic.pouch.session.beans.users.UsersFacadeLocal;

/**
 *
 * @author romanrudenko
 */
@ManagedBean
@ViewScoped
public class AdminMainPage implements Serializable{

    @EJB
    private UsersFacadeLocal usersFacade;

    @EJB
    private OrderTypesFacadeLocal orderTypesFacade;

    @EJB
    private CurrenciesFacadeLocal currenciesFacade;

    private Currencies currency = new Currencies();
    private List<Currencies> currencyList = new ArrayList<>();
    private OrderTypes orderType = new OrderTypes();
    private List<OrderTypes> orderTypeList = new ArrayList<>();
    private List<Users> userList = new ArrayList<>();

    public AdminMainPage() {
    }

    @PostConstruct
    public void init() {
        currencyList = currenciesFacade.findAll();
        orderTypeList = orderTypesFacade.findAll();
        userList = usersFacade.findAll();
    }

    public String addCurrency() {
        currenciesFacade.create(currency);
        return "adminMainPage";

    }

    public String addOrderType() {
        orderTypesFacade.create(orderType);
        return "adminMainPage";

    }

    public List<Currencies> getCurrenciesList() {
        return currencyList;
    }

    public void setCurrenciesList(List<Currencies> currenciesList) {
        this.currencyList = currenciesList;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public void setCurrency(Currencies currency) {
        this.currency = currency;
    }

    public OrderTypes getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypes orderType) {
        this.orderType = orderType;
    }

    public List<OrderTypes> getOrderTypeList() {
        return orderTypeList;
    }

    public void setOrderTypeList(List<OrderTypes> orderTypeList) {
        this.orderTypeList = orderTypeList;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

}
