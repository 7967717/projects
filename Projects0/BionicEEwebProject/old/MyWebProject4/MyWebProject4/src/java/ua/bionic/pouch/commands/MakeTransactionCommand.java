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
public class MakeTransactionCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String page;
        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAKE_TRANSACTION_PAGE_PATH);

        return page;    
    }
    
}
