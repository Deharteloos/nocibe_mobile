package pageObjects;

import config.Properties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    AppiumDriver driver;
    public static final long WAIT = 10;

    public Page() {
        driver = Properties.APPIUM_DRIVER_MANAGER.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void waitForVisibility(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clear(MobileElement element) {
        waitForVisibility(element);
        element.clear();
    }

    public void click(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendTextSlowly(MobileElement element, String text) {
        waitForVisibility(element);
        for (int i = 0; i < text.length(); i++) {
            element.sendKeys(text.substring(i, i + 1));
        }
    }

    public void sendText(MobileElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getAttribute(MobileElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

}
