/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.managed.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import ua.bionic.pouch.entities.Accounts;
import ua.bionic.pouch.entities.Currencies;
import ua.bionic.pouch.entities.OrderTypes;
import ua.bionic.pouch.entities.Orders;
import ua.bionic.pouch.entities.Transactions;
import ua.bionic.pouch.entities.Users;
import ua.bionic.pouch.session.beans.accounts.AccountsFacadeLocal;
import ua.bionic.pouch.session.beans.currencies.CurrenciesFacadeLocal;
import ua.bionic.pouch.session.beans.orderTypes.OrderTypesFacadeLocal;
import ua.bionic.pouch.session.beans.orders.OrdersFacadeLocal;
import ua.bionic.pouch.session.beans.transactions.TransactionsFacadeLocal;
import ua.bionic.pouch.session.beans.users.UsersFacadeLocal;

/**
 *
 * @author romanrudenko
 */
@ManagedBean
@SessionScoped
public class UserMainPage implements Serializable {

    @EJB
    private TransactionsFacadeLocal transactionsFacade;
    @EJB
    private OrdersFacadeLocal ordersFacade;
    @EJB
    private OrderTypesFacadeLocal orderTypesFacade;
    @EJB
    private CurrenciesFacadeLocal currenciesFacade;
    @EJB
    private AccountsFacadeLocal accountsFacade;
    @EJB
    private UsersFacadeLocal usersFacade;

    private static final Integer REFILL = 1;
    private static final Integer PAY = 2;
    private static final Integer SEND = 3;
    private static final Integer REQUEST = 4;

    private Users user = new Users();
    private Currencies currency = new Currencies();
    private List<Currencies> currencyList = new ArrayList<>();
    private Accounts newAccount = new Accounts();
    private Accounts selectedAccount = new Accounts();
    private Accounts contrAccount = new Accounts();
    private List<Accounts> accountList = new ArrayList<>();
    private OrderTypes orderType = new OrderTypes();
    private String orderString = new String();
    private List<OrderTypes> orderTypeList = new ArrayList<>();
    private Orders newOrder = new Orders();
    private Orders selectedOrder = new Orders();
    private List<Orders> orderList = new ArrayList<Orders>();
    private List<Orders> allOrdersList = new ArrayList<Orders>();
    private Transactions transaction = new Transactions();
    private List<Transactions> transactionList = new ArrayList<Transactions>();

    public UserMainPage() {
    }

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        setUser(usersFacade.findByLogin(context.getExternalContext().getUserPrincipal().getName()));
        accountList = accountsFacade.findByUserID(user);
        ordersFacade.findByAccountId(selectedAccount);
        transactionsFacade.findByAccountId(selectedAccount);
    }

    public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/faces/index";
    }

    public String addAccount() {
        currency = currenciesFacade.findByType(currency.getType());
        newAccount.setUserId(user);
        newAccount.setCurrencyId(currency);
        newAccount.setBalance(BigDecimal.ZERO);
        accountsFacade.create(newAccount);
        return "UserMainPage?faces-redirect=true";
    }

    public String createOrder() {
        orderType = orderTypesFacade.findByType(orderType.toString());
        newOrder.setUserId(user);
        newOrder.setAccountId(selectedAccount);
        newOrder.setOrderTypeId(orderType);
        ordersFacade.create(newOrder);
        return "createOrder";
    }

    public String createOrderFromExisting() {
        orderType = selectedOrder.getOrderTypeId();
        newOrder.setUserId(user);
        newOrder.setAccountId(selectedAccount);
        newOrder.setOrderTypeId(orderType);
        newOrder.setAmount(selectedOrder.getAmount());
        ordersFacade.create(newOrder);
        return "viewOrders";
    }

    public String confirmTransaction() {
        transaction.setAccountId(selectedAccount);
        transaction.setUserId(user);
        transaction.setOrderId(selectedOrder);

        if (selectedOrder.getConfirmed() == false) {
            selectedOrder.setConfirmed(true);
            if (selectedOrder.getOrderTypeId().getId().equals(REFILL)) {
                selectedAccount.setBalance(selectedAccount.getBalance().add(selectedOrder.getAmount()));
                accountsFacade.edit(selectedAccount);
            } else if (selectedOrder.getOrderTypeId().getId().equals(PAY)) {
                selectedAccount.setBalance(selectedAccount.getBalance().subtract(selectedOrder.getAmount()));
                accountsFacade.edit(selectedAccount);
            } 
//            else if (selectedOrder.getOrderTypeId().getId().equals(SEND)
//                    || selectedOrder.getOrderTypeId().getId().equals(REQUEST)) {
//                selectedAccount.setBalance(selectedAccount.getBalance().subtract(selectedOrder.getAmount()));
//                contrAccount.setBalance(contrAccount.getBalance().add(selectedOrder.getAmount()));
//                accountsFacade.edit(selectedAccount);
//                accountsFacade.edit(contrAccount);
//            }
        }

        ordersFacade.edit(selectedOrder);
        transactionsFacade.create(transaction);

        return "confirmTransaction";
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public void setCurrency(Currencies currency) {
        this.currency = currency;
    }

    public List<Currencies> getCurrenciesList() {
        return currenciesFacade.findAll();
    }

    public void setCurrenciesList(List<Currencies> currenciesList) {
        this.currencyList = currenciesList;
    }

    public Accounts getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(Accounts newAccount) {
        this.newAccount = newAccount;
    }

    public List<Accounts> getAccountList() {
        return accountsFacade.findByUserID(user);
    }

    public void setAccountList(List<Accounts> accountList) {
        this.accountList = accountList;
    }

    public Accounts getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Accounts selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public OrderTypes getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypes orderType) {
        this.orderType = orderType;
    }

    public List<OrderTypes> getOrderTypeList() {
        return orderTypesFacade.findAll();
    }

    public void setOrderTypeList(List<OrderTypes> orderTypeList) {
        this.orderTypeList = orderTypeList;
    }

    public Orders getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(Orders newOrder) {
        this.newOrder = newOrder;
    }

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }

    public List<Orders> getOrderList() {
        return ordersFacade.findByAccountIdAndConfirmed(selectedAccount, false);
    }

    public void setOrderList(List<Orders> orderList) {
        this.orderList = orderList;
    }

    public Orders getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Orders selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }

    public List<Transactions> getTransactionList() {
        return transactionsFacade.findByAccountId(selectedAccount);
    }

    public void setTransactionList(List<Transactions> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Orders> getAllOrdersList() {
        return ordersFacade.findByAccountId(selectedAccount);
    }

    public void setAllOrdersList(List<Orders> allOrdersList) {
        this.allOrdersList = allOrdersList;
    }

}
