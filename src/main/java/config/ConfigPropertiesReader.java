package config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public enum ConfigPropertiesReader {

    INSTANCE;

    private static final String PROPERTIES_FILENAME = "config.properties";
    private static final String PROPERTIES_LOCATION = "config/" + PROPERTIES_FILENAME;

    private Properties properties;

    public final String appiumServer;
    public final String app;
    public final String appPackage;
    public final String appActivity;

    public static ConfigPropertiesReader getInstance() {
        return INSTANCE;
    }

    private String readProperty(String key) {
        String property = properties.getProperty(key);
        if(property == null || property.isEmpty()) {
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
        appiumServer = readProperty("appiumServer");
        app = readProperty("app");
        appPackage = readProperty("appPackage");
        appActivity = readProperty("appActivity");
    }

}
