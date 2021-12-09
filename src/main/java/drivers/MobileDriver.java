package drivers;

import config.Properties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;

public class MobileDriver implements Driver {

    private final AppiumDriver<MobileElement> driver;

    public MobileDriver(String platform) {
        driver = createMobileDriver(platform);
    }

    private AppiumDriver<MobileElement> createMobileDriver(String platform) {
        URL appiumServerUrl = getAppiumServer("http://"+Properties.CONFIG_PROPERTIES_READER.appiumServerHost+":"+Properties.CONFIG_PROPERTIES_READER.appiumServerPort+"/wd/hub");
        return platform.equals("Android") ? new AndroidDriver<MobileElement>(appiumServerUrl, getAndroidCapabilities()) :
                                            new IOSDriver<MobileElement>(appiumServerUrl, getIOSDesiredCapabilities());
    }

    @Override
    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    @Override
    public void closeDriver() {
        driver.close();
    }



}
