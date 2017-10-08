package ua.bionic.pouch.daoImpl;

import ua.bionic.pouch.beans.User;
import ua.bionic.pouch.dao.IUserDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.bionic.pouch.connection.ConnectionPool;

public class UserDaoImpl implements IUserDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void create(User user) {

        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "INSERT INTO user(user_type_id, name, surname, age, login, password) VALUES(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, user.getUserTypeId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.executeUpdate();
            Logger.getLogger(OrderTransDaoImpl.class.getName()).log(Level.INFO, "user created");
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }

    @Override
    public User read(String login) {
        User user = new User();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "SELECT * FROM user WHERE login=?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setIdUser(resultSet.getInt("id_user"));
                user.setUserTypeId(resultSet.getInt("user_type_id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setAge(resultSet.getInt("age"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return user;
    }

    @Override
    public boolean checkLogin(String login, String password) {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE login=? AND password=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally {
        closeConnection();
        }
    }

    @Override
    public void update(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "SELECT * FROM user";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt("id_user"));
                user.setUserTypeId(resultSet.getInt("user_type_id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setAge(resultSet.getInt("age"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return users;
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
