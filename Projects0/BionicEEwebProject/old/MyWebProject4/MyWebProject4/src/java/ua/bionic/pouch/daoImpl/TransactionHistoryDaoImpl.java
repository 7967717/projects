package ua.bionic.pouch.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ua.bionic.pouch.beans.TransactionHistory;
import ua.bionic.pouch.dao.ITransactionHistoryDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.bionic.pouch.connection.ConnectionPool;

public class TransactionHistoryDaoImpl implements ITransactionHistoryDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void create(TransactionHistory transactionHistory) {

        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "INSERT INTO transaction_history(date, order_id, "
                    + "account_id, user_id) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setDate(1, transactionHistory.getDate());
            preparedStatement.setInt(2, transactionHistory.getOrderId());
            preparedStatement.setInt(3, transactionHistory.getAccountId());
            preparedStatement.setInt(4, transactionHistory.getUserId());
            preparedStatement.executeUpdate();
            Logger.getLogger(TransactionHistoryDaoImpl.class.getName()).log(Level.INFO, "transaction created");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }

    @Override
    public TransactionHistory readByAccountId(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(TransactionHistory transactionHistory) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(TransactionHistory transactionHistory) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<TransactionHistory> findAll() {
        List<TransactionHistory> transactionHistoryss = new ArrayList<TransactionHistory>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "SELECT * FROM transaction_history";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TransactionHistory transactionHistorys = new TransactionHistory();
                transactionHistorys.setIdTrans(resultSet.getInt("id_trans"));
                transactionHistorys.setOrderId(resultSet.getInt("order_id"));
                transactionHistorys.setDate(resultSet.getDate("date"));
                transactionHistorys.setAccountId(resultSet.getInt("account_id"));
                transactionHistorys.setUserId(resultSet.getInt("user_id"));
                transactionHistoryss.add(transactionHistorys);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return transactionHistoryss;
    }

    private void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
