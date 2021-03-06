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
public class ShowOrdersCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page;
        String login = (String) request.getSession(true).getAttribute("login");

        IUserDao userDao = new UserDaoImpl();
        IAccountDao accountDao = new AccountDaoImpl();
        ICurrencyDao currencyDao = new CurrencyDaoImpl();

        User user = userDao.read(login);
        Account account = accountDao.readByUserId(user.getIdUser());
        
        List<Currency> currencies = currencyDao.findAll();

        request.getSession(true).setAttribute("user", user);
        request.getSession(true).setAttribute("account", account);
        request.getSession(true).setAttribute("currencies", currencies);

        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.SHOW_ORDERS_PAGE_PATH);
        return page;

    }
}
