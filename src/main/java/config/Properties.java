package config;

import drivers.AppiumDriverManager;

public interface Properties {

    ConfigPropertiesReader CONFIG_PROPERTIES_READER = ConfigPropertiesReader.getInstance();
    SystemPropertiesReader SYSTEM_PROPERTIES_READER = SystemPropertiesReader.getInstance();
    AppiumDriverManager    APPIUM_DRIVER_MANAGER    = AppiumDriverManager.getInstance();

}
