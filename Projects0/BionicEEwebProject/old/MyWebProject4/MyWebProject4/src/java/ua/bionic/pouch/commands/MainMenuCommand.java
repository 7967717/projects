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
import ua.bionic.pouch.beans.User;
import ua.bionic.pouch.beans.UserType;
import ua.bionic.pouch.dao.IUserDao;
import ua.bionic.pouch.dao.IUserTypeDao;
import ua.bionic.pouch.daoImpl.UserDaoImpl;
import ua.bionic.pouch.daoImpl.UserTypeDaoImpl;
import ua.bionic.pouch.manager.ConfigurationManager;

/**
 *
 * @author romanrudenko
 */
public class MainMenuCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        String login = (String) request.getSession(true).getAttribute("login");

        IUserDao userDao = new UserDaoImpl();
        IUserTypeDao userTypeDao = new UserTypeDaoImpl();

        User user = userDao.read(login);
        
        List<UserType> userTypes = userTypeDao.findAll();

        request.getSession(true).setAttribute("user", user);
        request.getSession(true).setAttribute("userTypes", userTypes);

        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);

        return page;
    }
}
