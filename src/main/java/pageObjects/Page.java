package pageObjects;

import config.ConfigPropertiesReader;
import config.Properties;
import config.SystemPropertiesReader;
import drivers.MobileDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class Page {

    private static final Logger LOG = LogManager.getLogger(Page.class);

    protected AppiumDriver<MobileElement> driver;
    protected AndroidTouchAction actions;

    protected WebDriverWait shortWait;
    protected WebDriverWait wait;
    protected WebDriverWait longWait;
    protected WebDriverWait loadingWait;
    public static final long SHORT_WAIT = 2;
    public static final long WAIT = 5;
    public static final long LONG_WAIT = 10;
    public static final long LOADING_WAIT = 30;

    protected SystemPropertiesReader systemPropertiesReader;
    protected ConfigPropertiesReader configPropertiesReader;

    public Page() {
        driver      = Properties.APPIUM_DRIVER_MANAGER.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        actions     = new AndroidTouchAction(driver);
        wait        = new WebDriverWait(driver, WAIT);
        shortWait   = new WebDriverWait(driver, SHORT_WAIT);
        longWait    = new WebDriverWait(driver, LONG_WAIT);
        loadingWait = new WebDriverWait(driver, LOADING_WAIT);
        systemPropertiesReader = Properties.SYSTEM_PROPERTIES_READER;
        configPropertiesReader = Properties.CONFIG_PROPERTIES_READER;
    }

    /**
     * Wait until the condition in the function is satisfied
     * @param isTrue the condition
     * @param <V> the condition return type
     * @return true if the condition is satisfied, false if the condition hasn't been satisfied in the given time
     */
    protected <V> boolean shortWaitUntil(Function<? super WebDriver, V> isTrue) {
        try {
            shortWait.until(isTrue);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected <V> boolean waitUntil(Function<? super WebDriver, V> isTrue) {
        try {
            wait.until(isTrue);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected <V> boolean longWaitUntil(Function<? super WebDriver, V> isTrue) {
        try {
            this.longWait.until(isTrue);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected <V> boolean loadingWaitUntil(Function<? super WebDriver, V> isTrue) {
        try {
            this.loadingWait.until(isTrue);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * For applications that have a splashscreen, this function waits for the splashscreen to be crossed
     * @param splashScreen
     */
    public void waitForAppLoading(MobileElement splashScreen) {
        if(loadingWaitUntil(ExpectedConditions.invisibilityOf(splashScreen))) {
            LOG.info("The application has successfully been loaded");
        } else {
            LOG.warn("The application is stucked on the splash screen");
        }

    }

    protected void waitForVisibility(MobileElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void get(String url) {
        driver.get(url);
    }

    protected void clear(MobileElement element) {
        waitForVisibility(element);
        element.clear();
    }

    protected void click(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }

    protected void sendTextSlowly(MobileElement element, String text) {
        waitForVisibility(element);
        for (int i = 0; i < text.length(); i++) {
            element.sendKeys(text.substring(i, i + 1));
        }
    }

    protected void sendText(MobileElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    protected String getAttribute(MobileElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    protected void scrollDown() {
        Dimension dimension = driver.manage().window().getSize();
        actions.press(PointOption.point(0, (int) (dimension.getHeight() * 0.8)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(0, (int)((int) (dimension.getHeight() * 0.2))))
                .release()
                .perform();
    }

    protected void scrollUp() {
        Dimension dimension = driver.manage().window().getSize();
        actions.press(PointOption.point(0, (int) (dimension.getHeight() * 0.2)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(0, (int)((int) (dimension.getHeight() * 0.8))))
                .release()
                .perform();
    }

}
