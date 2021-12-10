package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public enum ConfigPropertiesReader {

    INSTANCE;

    private static final Logger LOG = LogManager.getLogger(ConfigPropertiesReader.class);

    private static final String PROPERTIES_FILENAME = "config.properties";
    private static final String PROPERTIES_LOCATION = "config/" + PROPERTIES_FILENAME;

    private Properties properties;

    public final String appiumServerHost;
    public final int appiumServerPort;
    public final String app;
    public final String appPackage;
    public final String appActivity;

    public static ConfigPropertiesReader getInstance() {
        return INSTANCE;
    }

    private String readProperty(String key) {
        String property = properties.getProperty(key);
        if(property == null || property.isEmpty()) {
            //TODO Verify why the logger is not working here
            System.out.println(key+ "value is missing in config.properties");
        }
        return property;
    }

    private void loadProperties() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(PROPERTIES_LOCATION));
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ConfigPropertiesReader() {
        loadProperties();
        appiumServerHost = readProperty("appiumServerHost");
        appiumServerPort = Integer.parseInt(readProperty("appiumServerPort"));
        app = readProperty("app");
        appPackage = readProperty("appPackage");
        appActivity = readProperty("appActivity");
    }

}
