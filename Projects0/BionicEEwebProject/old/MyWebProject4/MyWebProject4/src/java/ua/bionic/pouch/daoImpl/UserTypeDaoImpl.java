package ua.bionic.pouch.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ua.bionic.pouch.beans.UserType;
import ua.bionic.pouch.dao.IUserTypeDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.bionic.pouch.connection.ConnectionPool;

public class UserTypeDaoImpl implements IUserTypeDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void create(UserType userType) {

        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "INSERT INTO user_type(id_user_type, user_desc)"
                    + " VALUES(?,?)";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, userType.getIdUserType());
            preparedStatement.setString(2, userType.getUserDesc());

            preparedStatement.executeUpdate();
            Logger.getLogger(OrderTransDaoImpl.class.getName()).log(Level.INFO, "userType created");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }

    @Override
    public UserType read(int id) {
        UserType userType = new UserType();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "SELECT * FROM user_type WHERE id_user_type=?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userType.setIdUserType(resultSet.getInt("id_user_type"));
                userType.setUserDesc(resultSet.getString("user_desc"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserTypeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return userType;
    }

    @Override
    public void update(UserType userType) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(UserType userType) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<UserType> findAll() {
        List<UserType> userTypes = new ArrayList<UserType>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "SELECT * FROM user_type";
            preparedStatement = connection.prepareStatement(queryString);
            ResultSet resultSet = null;
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserType userType = new UserType();
                userType.setIdUserType(resultSet.getInt("id_user_type"));
                userType.setUserDesc(resultSet.getString("user_desc"));
                userTypes.add(userType);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserTypeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return userTypes;
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
