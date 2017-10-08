/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.bionic.pouch.beans.TransactionType;
import ua.bionic.pouch.connection.ConnectionPool;
import ua.bionic.pouch.dao.ITransactionTypeDao;

/**
 *
 * @author romanrudenko
 */
public class TransactionTypeDaoImpl implements ITransactionTypeDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void create(TransactionType transactionType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TransactionType read(TransactionType transactionType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TransactionType transactionType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TransactionType transactionType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TransactionType> findAll() {
        List<TransactionType> transactionTypes = new ArrayList<TransactionType>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "SELECT * FROM transaction_type";
            preparedStatement = connection.prepareStatement(queryString);
            ResultSet resultSet = null;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TransactionType transactionType = new TransactionType();
                transactionType.setIdTransType(resultSet.getInt("id_trans_type"));
                transactionType.setTransDesc(resultSet.getString("trans_desc"));
                transactionTypes.add(transactionType);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionTypeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return transactionTypes;
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
            Logger.getLogger(UserTypeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
