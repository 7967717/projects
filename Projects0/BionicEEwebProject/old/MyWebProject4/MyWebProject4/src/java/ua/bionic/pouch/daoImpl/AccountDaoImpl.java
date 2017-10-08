package ua.bionic.pouch.daoImpl;

import ua.bionic.pouch.beans.Account;
import ua.bionic.pouch.dao.IAccountDao;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.bionic.pouch.connection.ConnectionPool;

public class AccountDaoImpl implements IAccountDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void create(Account account) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Account readByUserId(int id) {
        Account account = new Account();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "SELECT * FROM account WHERE user_id=?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account.setIdAccount(resultSet.getInt("id_account"));
                account.setCurrencyId(resultSet.getInt("currency_id"));
                account.setBalance(resultSet.getLong("balance"));
                account.setUserId(resultSet.getInt("user_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return account;
    }

    @Override
    public void update(Account account) {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "UPDATE account SET balance=? WHERE id_account=?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setLong(1, account.getBalance());
            preparedStatement.setInt(2, account.getIdAccount());
            preparedStatement.executeUpdate();
            Logger.getLogger(AccountDaoImpl.class.getName()).log(Level.INFO, "account updated");
        } catch (SQLException ex) {
            Logger.getLogger(AccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }

    @Override
    public void delete(Account account) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findAll() {
        //To change body of implemented methods use File | Settings | File Templates.
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
            Logger.getLogger(AccountDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
