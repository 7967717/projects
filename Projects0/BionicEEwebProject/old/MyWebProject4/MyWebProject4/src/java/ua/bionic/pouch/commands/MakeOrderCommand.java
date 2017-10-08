/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.commands;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.bionic.pouch.beans.*;
import ua.bionic.pouch.dao.*;
import ua.bionic.pouch.daoImpl.*;
import ua.bionic.pouch.manager.ConfigurationManager;

/**
 *
 * @author romanrudenko
 */
public class MakeOrderCommand implements ICommand {

    private static final String PARAM_NAME_TRANSACTION_TYPE = "transactionType";
    private static final String PARAM_NAME_SUM = "sum";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        String login = (String) request.getSession(true).getAttribute("login");
        int transactionType = Integer.parseInt(request.getParameter(PARAM_NAME_TRANSACTION_TYPE));
        long sum = Long.parseLong(request.getParameter(PARAM_NAME_SUM));

        IUserDao userDao = new UserDaoImpl();
        IAccountDao accountDao = new AccountDaoImpl();
        ICurrencyDao currencyDao = new CurrencyDaoImpl();
        IOrderTransDao orderTransDao = new OrderTransDaoImpl();

        User user = userDao.read(login);
        Account account = accountDao.readByUserId(user.getIdUser());
        OrderTrans orderTrans = new OrderTrans(transactionType, sum,
                account.getIdAccount(), user.getIdUser());
        orderTransDao.create(orderTrans);
        
        List<Currency> currencies = currencyDao.findAll();
        List<OrderTrans> orderTranss = orderTransDao.findAll();

            request.getSession(true).setAttribute("user", user);
            request.getSession(true).setAttribute("account", account);
            request.getSession(true).setAttribute("currencies", currencies);
            request.getSession(true).setAttribute("orderTrans", orderTrans);
            request.getSession(true).setAttribute("orderTranss", orderTranss);

        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.SHOW_ORDERS_PAGE_PATH);
        return page;
    }
}
