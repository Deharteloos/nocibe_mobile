import config.Properties;
import drivers.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestRunner {

    public static AppiumDriver driver;

    public static void Android_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("app",
                System.getProperty("user.dir") + "/apks/ToDo_v1.24_apkpure.com.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @Parameters({"platform"})
    @BeforeClass
    public void beforeTest(@Optional String platform) {
        AppiumDriverManager.setDriver(java.util.Optional
                .ofNullable(platform)
                .orElse(Properties.SYSTEM_PROPERTIES_READER.platformName));
    }

    @Test
    public void test() {
        System.out.println(Properties.CONFIG_PROPERTIES_READER.appiumServer);
        System.out.println(Properties.CONFIG_PROPERTIES_READER.app);
        System.out.println(Properties.CONFIG_PROPERTIES_READER.appActivity);
        System.out.println(Properties.CONFIG_PROPERTIES_READER.appPackage);
        System.out.println(Properties.SYSTEM_PROPERTIES_READER.deviceName);
        System.out.println(Properties.SYSTEM_PROPERTIES_READER.env.getUrl());
    }

    public static void tearDown() {
        if(null != driver) {
            driver.quit();
        }
    }

}
