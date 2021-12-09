package drivers;

import io.appium.java_client.AppiumDriver;

public class AppiumDriverManager {

    private static final AppiumDriverManager INSTANCE = new AppiumDriverManager();

    private static ThreadLocal<Driver> driver = new ThreadLocal<>();

    private AppiumDriverManager() {
    }

    public static AppiumDriverManager getInstance() {
        return INSTANCE;
    }

    public static AppiumDriver getDriver() {
        return driver.get().getDriver();
    }

    public static void setDriver(String platform) {
        driver.set(DriverFactory.getDriver(platform));
    }

}
