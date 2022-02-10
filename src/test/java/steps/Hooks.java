package steps;

import drivers.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.HomePage;

public class Hooks {

    private static final Logger LOG = LogManager.getLogger(Hooks.class);

    private HomePage homePage;
    private AppiumDriver<MobileElement> driver = AppiumDriverManager.getDriver();

    public Hooks(HomePage homePage) {
        this.homePage = homePage;
    }

    @After
    public void closeApp() {
        driver.closeApp();
    }

    @Before()
    public void launchApp() {
        driver.launchApp();
    }

}
