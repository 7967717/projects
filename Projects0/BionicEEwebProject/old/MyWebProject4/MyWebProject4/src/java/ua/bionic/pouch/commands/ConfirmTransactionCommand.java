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
public class ConfirmTransactionCommand implements ICommand {

    private static final String PARAM_NAME_ORDER_ID = "orderId";
    private static final String PARAM_NAME_CONFIRMED = "confirm";
    private static final int TRANSACTION_TYPE_REFILL = 1;
    private static final int TRANSACTION_TYPE_PAYMENT = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;

        String login = (String) request.getSession(true).getAttribute("login");
        int orderId = Integer.parseInt(request.getParameter(PARAM_NAME_ORDER_ID));
        boolean confirmed = Boolean.parseBoolean(request.getParameter(PARAM_NAME_CONFIRMED));

        IUserDao userDao = new UserDaoImpl();
        IAccountDao accountDao = new AccountDaoImpl();
        IOrderTransDao orderTransDao = new OrderTransDaoImpl();
        ITransactionHistoryDao transactionHistoryDao = new TransactionHistoryDaoImpl();

        User user = userDao.read(login);
        Account account = accountDao.readByUserId(user.getIdUser());
        OrderTrans orderTrans = orderTransDao.read(orderId);
        orderTrans.setConfirmed(confirmed);
        orderTransDao.updateConfirmed(orderTrans);

        if (orderTrans.getConfirmed() == true) {
            if (orderTrans.getTransTypeId() == TRANSACTION_TYPE_REFILL) {
                account.setBalance(account.getBalance() + orderTrans.getSum());
                accountDao.update(account);
            }
            if (orderTrans.getTransTypeId() == TRANSACTION_TYPE_PAYMENT) {
                account.setBalance(account.getBalance() - orderTrans.getSum());
                accountDao.update(account);
            }
            TransactionHistory transactionHistory = new TransactionHistory(
                    orderTrans.getIdOrder(), orderTrans.getAccountId(), orderTrans.getUserId());
            transactionHistoryDao.create(transactionHistory);

            List<TransactionHistory> transactionHistorys = transactionHistoryDao.findAll();
            List<OrderTrans> orderTranss = orderTransDao.findAll();

            request.getSession(true).setAttribute("user", user);
            request.getSession(true).setAttribute("account", account);
            request.getSession(true).setAttribute("orderTrans", orderTrans);
            request.getSession(true).setAttribute("orderTranss", orderTranss);
            request.getSession(true).setAttribute("transactionHistory", transactionHistory);
            request.getSession(true).setAttribute("transactionHistorys", transactionHistorys);

        }
        page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAKE_TRANSACTION_PAGE_PATH);

        return page;
    }
}
