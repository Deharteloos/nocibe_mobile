package drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumDriverManager {

    private static final AppiumDriverManager INSTANCE = new AppiumDriverManager();

    private static ThreadLocal<MobileDriver> driver = new ThreadLocal<>();

    private AppiumDriverManager() {
    }

    public static AppiumDriverManager getInstance() {
        return INSTANCE;
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return driver.get().getDriver();
    }

    public static void setDriver(String platform) {
        driver.set(DriverFactory.getDriver(platform));
    }

}
