/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import ua.bionic.pouch.commands.*;

/**
 *
 * @author romanrudenko
 */
class RequestHelper {

    private static RequestHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    private RequestHelper() {
        commands.put("Login", new LoginCommand());
        commands.put("Show My Account", new ShowAccountCommand());
        commands.put("Show Orders", new ShowOrdersCommand());
        commands.put("Make Order", new MakeOrderCommand());
        commands.put("Make Transaction", new MakeTransactionCommand());
        commands.put("Confirm Transaction", new ConfirmTransactionCommand());
        commands.put("Show Transactions", new ShowTransactionsCommand());
        commands.put("Main Menu", new MainMenuCommand());
        commands.put("Logout", new LogoutCommand());
    }

    public ICommand getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        ICommand command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
