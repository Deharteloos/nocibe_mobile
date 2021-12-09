package drivers;

import config.Properties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;

public class MobileDriver implements Driver {

    private final AppiumDriver driver;

    public MobileDriver(String platform) {
        driver = createMobileDriver(platform);
    }

    private AppiumDriver createMobileDriver(String platform) {
        URL appiumServerUrl = getAppiumServer(Properties.CONFIG_PROPERTIES_READER.appiumServer+"/wd/hub");
        return platform.equals("Android") ? new AndroidDriver(appiumServerUrl, getAndroidCapabilities()) :
                                            new IOSDriver(appiumServerUrl, getIOSDesiredCapabilities());
    }

    @Override
    public AppiumDriver getDriver() {
        return driver;
    }

    @Override
    public void closeDriver() {
        driver.close();
    }



}
