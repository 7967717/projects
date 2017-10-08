/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.commands;

import ua.bionic.pouch.beans.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.bionic.pouch.beans.*;
import ua.bionic.pouch.dao.*;
import ua.bionic.pouch.daoImpl.*;
import ua.bionic.pouch.manager.ConfigurationManager;
import ua.bionic.pouch.manager.MessageManager;

/**
 *
 * @author romanrudenko
 */
public class LoginCommand implements ICommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        IAccountDao accountDao = new AccountDaoImpl();
        ICurrencyDao currencyDao = new CurrencyDaoImpl();
        IOrderTransDao orderTransDao = new OrderTransDaoImpl();
        ITransactionHistoryDao transactionHistoryDao = new TransactionHistoryDaoImpl();
        ITransactionTypeDao transactionTypeDao = new TransactionTypeDaoImpl();
        IUserDao userDao = new UserDaoImpl();
        IUserTypeDao userTypeDao = new UserTypeDaoImpl();

        User user = userDao.read(login);
        Account account = accountDao.readByUserId(user.getIdUser());
        OrderTrans orderTrans = new OrderTrans();
        TransactionHistory transactionHistory = new TransactionHistory();

        List<Currency> currencies = currencyDao.findAll();
        List<UserType> userTypes = userTypeDao.findAll();
        List<TransactionType> transactionTypes = transactionTypeDao.findAll();
        List<OrderTrans> orderTranss = orderTransDao.findAll();
        List<TransactionHistory> transactionHistorys = transactionHistoryDao.findAll();

        if (userDao.checkLogin(login, password)) {

            request.getSession(true).setAttribute("login", login);
            request.getSession(true).setAttribute("user", user);
            request.getSession(true).setAttribute("userTypes", userTypes);
            request.getSession(true).setAttribute("account", account);
            request.getSession(true).setAttribute("currencies", currencies);
            request.getSession(true).setAttribute("transactionTypes", transactionTypes);
            request.getSession(true).setAttribute("orderTrans", orderTrans);
            request.getSession(true).setAttribute("orderTranss", orderTranss);
            request.getSession(true).setAttribute("transactionHistory", transactionHistory);
            request.getSession(true).setAttribute("transactionHistorys", transactionHistorys);

            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } else {
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }

        return page;
    }
}
