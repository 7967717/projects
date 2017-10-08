/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.bionic.pouch.manager.ConfigurationManager;

/**
 *
 * @author romanrudenko
 */
public class LogoutCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page;
        request.getSession(true).setAttribute("login", null);
        request.getSession(true).setAttribute("user", null);
        request.getSession(true).setAttribute("account", null);
        request.getSession(true).setAttribute("transactionTypes", null);
        request.getSession(true).setAttribute("orderTrans", null);
        request.getSession(true).setAttribute("orderTranss", null);
        request.getSession(true).setAttribute("transactionHistory", null);
        request.getSession(true).setAttribute("transactionHistorys", null);
        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        request.getSession().invalidate();
        return page;
    }
}
