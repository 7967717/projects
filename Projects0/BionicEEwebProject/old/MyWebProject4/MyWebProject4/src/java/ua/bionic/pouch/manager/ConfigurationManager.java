/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.manager;

import java.util.ResourceBundle;

/**
 *
 * @author romanrudenko
 */
public class ConfigurationManager {
    
    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;
    
    private static final String BUNDLE_NAME = "ua.bionic.pouch.manager.config";
    public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
    public static final String DATABASE_URL = "DATABASE_URL";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String SHOW_ACCOUNT_PAGE_PATH = "SHOW_ACCOUNT_PAGE_PATH";
    public static final String SHOW_ORDERS_PAGE_PATH = "SHOW_ORDERS_PAGE_PATH";
    public static final String MAKE_TRANSACTION_PAGE_PATH = "MAKE_TRANSACTION_PAGE_PATH";
    public static final String SHOW_TRANSACTIONS_PAGE_PATH = "SHOW_TRANSACTIONS_PAGE_PATH";


    
    public static ConfigurationManager getInstance(){
        if (instance == null){
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }
    
    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
    
}
