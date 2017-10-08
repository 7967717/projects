/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.bionic.pouch.beans.Account;
import ua.bionic.pouch.beans.User;
import ua.bionic.pouch.dao.IAccountDao;
import ua.bionic.pouch.dao.IUserDao;
import ua.bionic.pouch.daoImpl.AccountDaoImpl;
import ua.bionic.pouch.daoImpl.UserDaoImpl;
import ua.bionic.pouch.manager.ConfigurationManager;

/**
 *
 * @author romanrudenko
 */
public class ShowAccountCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page;
        String login = (String) request.getSession(true).getAttribute("login");

        IUserDao userDao = new UserDaoImpl();
        IAccountDao accountDao = new AccountDaoImpl();

        User user = userDao.read(login);
        Account account = accountDao.readByUserId(user.getIdUser());

        request.getSession(true).setAttribute("user", user);
        request.getSession(true).setAttribute("account", account);
        
        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.SHOW_ACCOUNT_PAGE_PATH);
        return page;
    }
}
