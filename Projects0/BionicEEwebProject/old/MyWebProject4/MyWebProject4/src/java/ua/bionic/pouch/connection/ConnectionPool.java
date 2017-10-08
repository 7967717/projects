/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.connection;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import ua.bionic.pouch.manager.ConfigurationManager;

/**
 *
 * @author romanrudenko
 */
public class ConnectionPool {

    private static ConnectionPool instance = null;
    private Connection connection = null;
    private BasicDataSource basicDataSource;

    private ConnectionPool() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(ConfigurationManager.getInstance().
                getProperty(ConfigurationManager.DATABASE_DRIVER_NAME));
        basicDataSource.setUrl(ConfigurationManager.getInstance().
                getProperty(ConfigurationManager.DATABASE_URL));
    }

    public Connection getConnection() {
        connection = null;
        try {
            connection = basicDataSource.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }
}
