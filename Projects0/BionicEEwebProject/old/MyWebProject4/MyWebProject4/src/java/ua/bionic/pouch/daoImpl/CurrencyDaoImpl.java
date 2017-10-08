package ua.bionic.pouch.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ua.bionic.pouch.beans.Currency;
import ua.bionic.pouch.dao.ICurrencyDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.bionic.pouch.connection.ConnectionPool;

public class CurrencyDaoImpl implements ICurrencyDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void create(Currency currency) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Currency read(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Currency currency) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Currency currency) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<Currency>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            String queryString = "SELECT * FROM currency";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Currency currency = new Currency();
                currency.setIdCurrency(resultSet.getInt("id_currency"));
                currency.setCurrencyType(resultSet.getString("currency_type"));
                currencies.add(currency);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CurrencyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return currencies;
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
            Logger.getLogger(CurrencyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
