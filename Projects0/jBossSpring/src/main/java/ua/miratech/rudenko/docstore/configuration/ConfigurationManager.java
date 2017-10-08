package ua.miratech.rudenko.docstore.configuration;

import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

/**
 * Created by RomanR on 3/4/14.
 */
@Component
public class ConfigurationManager {

    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "config";

    private static final String ARTICLE_INDEX_STATUS_N = "ARTICLE_INDEX_STATUS_N";
    private static final String ARTICLE_INDEX_STATUS_Y = "ARTICLE_INDEX_STATUS_Y";
    private static final String FILE_TYPE = "FILE_TYPE";
    private static final String PATH = "PATH";
    private static final String NEW_INDEX = "NEW_INDEX";
    private static final String INDEX_PATH = "INDEX_PATH";
    private static final String MAX_HITS = "MAX_HITS";
    private static final String MAIN_PATH = "MAIN_PATH";
    private static final String EXT = "EXT";
    private static final String ORIGINAL = "ORIGINAL";
    private static final String DUPLICATE = "DUPLICATE";
    private static final String ENABLED = "ENABLED";
    private static final String AUTHORITY = "AUTHORITY";

    private static final String USER_EXIST = "USER_EXIST";
    private static final String PASSWORD_DO_NOT_MATCH = "PASSWORD_DO_NOT_MATCH";


    public ConfigurationManager() {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
