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
import ua.bionic.pouch.beans.OrderTrans;
import ua.bionic.pouch.connection.ConnectionPool;
import ua.bionic.pouch.dao.IOrderTransDao;

/**
 *
 * @author romanrudenko
 */
public class OrderTransDaoImpl implements IOrderTransDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void create(OrderTrans orderTrans) {

        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "INSERT INTO order_trans(trans_type_id, "
                    + "date, sum, confirmed, account_id, user_id) VALUES(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, orderTrans.getTransTypeId());
            preparedStatement.setDate(2, orderTrans.getDate());
            preparedStatement.setLong(3, orderTrans.getSum());
            preparedStatement.setBoolean(4, false);
            preparedStatement.setInt(5, orderTrans.getAccountId());
            preparedStatement.setInt(6, orderTrans.getUserId());
            preparedStatement.executeUpdate();
            Logger.getLogger(OrderTransDaoImpl.class.getName()).log(Level.INFO, "order created");
        } catch (SQLException ex) {
            Logger.getLogger(OrderTransDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }

    @Override
    public OrderTrans read(int id) {
        OrderTrans orderTrans = new OrderTrans();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "SELECT * FROM order_trans WHERE id_order=?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                orderTrans.setIdOrder(resultSet.getInt("id_order"));
                orderTrans.setTransTypeId(resultSet.getInt("trans_type_id"));
                orderTrans.setDate(resultSet.getDate("date"));
                orderTrans.setSum(resultSet.getLong("sum"));
                orderTrans.setConfirmed(resultSet.getBoolean("confirmed"));
                orderTrans.setAccountId(resultSet.getInt("account_id"));
                orderTrans.setUserId(resultSet.getInt("user_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderTransDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return orderTrans;
    }

    @Override
    public void updateConfirmed(OrderTrans orderTrans) {

        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "UPDATE order_trans SET confirmed=? WHERE id_order=?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setBoolean(1, orderTrans.getConfirmed());
            preparedStatement.setInt(2, orderTrans.getIdOrder());
            preparedStatement.executeUpdate();
            Logger.getLogger(OrderTransDaoImpl.class.getName()).log(Level.INFO, "order updated");
        } catch (SQLException ex) {
            Logger.getLogger(OrderTransDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }

    @Override
    public void delete(OrderTrans orderTrans) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderTrans> findAll() {
        List<OrderTrans> orderTranss = new ArrayList<OrderTrans>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "SELECT * FROM order_trans";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderTrans orderTrans = new OrderTrans();
                orderTrans.setIdOrder(resultSet.getInt("id_order"));
                orderTrans.setAccountId(resultSet.getInt("account_id"));
                orderTrans.setTransTypeId(resultSet.getInt("trans_type_id"));
                orderTrans.setDate(resultSet.getDate("date"));
                orderTrans.setSum(resultSet.getLong("sum"));
                orderTrans.setConfirmed(resultSet.getBoolean("confirmed"));
                orderTranss.add(orderTrans);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderTransDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return orderTranss;
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
            Logger.getLogger(OrderTransDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
