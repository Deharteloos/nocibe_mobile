package pageObjects;

import com.google.common.collect.ImmutableMap;
import config.ConfigPropertiesReader;
import config.Properties;
import config.SystemPropertiesReader;
import enums.Direction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Page {

    private static final Logger LOG = LogManager.getLogger(Page.class);

    protected AppiumDriver<MobileElement> driver;
    protected Actions actions;
    protected TouchAction<?> touchAction;

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
        actions = new Actions(driver);
        if(!Properties.SYSTEM_PROPERTIES_READER.platformName.equals("Android"))
            touchAction = new IOSTouchAction(driver);
        else
            touchAction = new AndroidTouchAction(driver);
        wait        = new WebDriverWait(driver, WAIT);
        shortWait   = new WebDriverWait(driver, SHORT_WAIT);
        longWait    = new WebDriverWait(driver, LONG_WAIT);
        loadingWait = new WebDriverWait(driver, LOADING_WAIT);
        systemPropertiesReader = Properties.SYSTEM_PROPERTIES_READER;
        configPropertiesReader = Properties.CONFIG_PROPERTIES_READER;
    }

    @AndroidFindBy(id = "com.pictime.nocibe:id/titleTextView")
    private MobileElement viewTitle;

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

    protected void set(MobileElement element, String text) {
        this.clear(element);
        element.sendKeys(text);
        //this.wait.until(driver -> element.getText().equalsIgnoreCase(text));
    }

    protected void click(MobileElement element) {
        if(!shortWaitUntil(visibilityOf(element)))
            LOG.warn("The element is not yet visible");
        if(!shortWaitUntil(elementToBeClickable(element)))
            LOG.warn("The element is not yet clickable");
        element.click();
    }

    protected boolean waitForVisibility(MobileElement element) {
        return this.waitUntil(visibilityOf(element));
    }

    protected void longPress(MobileElement element) {
        if(waitUntil(elementToBeClickable(element)))
            this.actions.clickAndHold(element)
                             .perform();
    }

    /**
     * Swipe from one point to another
     * @param from starting point
     * @param to ending point
     */
    protected void swipe(PointOption from, PointOption to) {
        touchAction.longPress(from)
                .moveTo(to)
                .release()
                .perform();
    }

    /**
     * Swipe in a given direction
     * @param direction The direction (UP, DOWN, LEFT or RIGHT)
     */
    protected void swipe(Direction direction) {
        Dimension size = this.driver.manage().window().getSize();
        switch (direction) {
            case DOWN:
                this.swipe(
                        PointOption.point(size.width / 2, size.height / 8),
                        PointOption.point(size.width / 2, size.height * 4 / 5)
                );
                break;

            case UP:
                this.swipe(
                        PointOption.point(size.width / 2, size.height * 4 / 5),
                        PointOption.point(size.width / 2, size.height / 8)
                );
                break;

            case LEFT:
                this.swipe(
                        PointOption.point(size.width * 9 / 10, size.height / 2),
                        PointOption.point(size.width / 20, size.height / 2)
                );
                break;

            case RIGHT:
                this.swipe(
                        PointOption.point(size.width / 20, size.height / 2),
                        PointOption.point(size.width * 9 / 10, size.height / 2)
                );
                break;
        }
    }

    /**
     * Swipe in the given direction "times" times
     * @param direction direction The direction (UP, DOWN, LEFT or RIGHT)
     * @param times number of time to swipe
     */
    protected void swipe(Direction direction, int times) {
        for (int i = 0; i < times; i++) {
            this.swipe(direction);
        }
    }

    protected void swipeScreen(Direction direction, int perc) {
        Dimension size = this.driver.manage().window().getSize();
        Integer fromX = null, fromY = null,
                toX = null, toY = null;

        switch (direction) {
            case UP:
                fromX = toX = size.width / 2;
                toY = 0;
                fromY = (int) ((double) size.height * (((double) perc) / 100));
                break;
            case DOWN:
                fromX = toX = size.width / 2;
                toY = (int) ((double) size.height * (((double) perc) / 100));
                fromY = 0;
                break;
        }
        swipe(
                PointOption.point(fromX, fromY),
                PointOption.point(toX, toY)
        );
    }

    protected void swipeScrollableElement(MobileElement scrollableWrap, Direction direction, int percOfScroll, Integer repeat) {
        int i = 0;
        Point wPosition = scrollableWrap.getLocation();
        Dimension wsize = scrollableWrap.getSize();
        repeat = repeat == null ? 1 : repeat;
        Integer fromX = null, fromY = null,
                toX = null, toY = null;

        switch (direction) {
            //TODO Review coordinates calculations in each case
            case UP:
                toY = wPosition.y;
                fromY = wPosition.y + (int) (((double) wsize.height) * ((double) percOfScroll / 100)) - 2;
                fromX = toX = wPosition.x + wsize.width / 2;
                break;
            case DOWN:
                fromY = wPosition.y;
                toY = wPosition.y + (int) (((double) wsize.height) * ((double) percOfScroll / 100)) - 2;
                fromX = toX = wPosition.x + wsize.width / 2;
                break;
            case RIGHT:
                fromX = wPosition.x;
                fromY = toY = wPosition.y + wsize.height / 2;
                toX = wPosition.x + (int) (((double) wsize.width) * ((double) percOfScroll / 100)) - 2;
                break;
            case LEFT:
                toX = wPosition.x;
                fromY = toY = wPosition.y + wsize.height / 2;
                fromX = wPosition.x + (int) (((double) wsize.width) * ((double) percOfScroll / 100)) - 2;
        }
        do {
            swipe(
                    PointOption.point(fromX, fromY),
                    PointOption.point(toX, toY)
            );
            i++;
        } while (i < repeat);
    }

    protected void get(String url) {
        driver.get(url);
    }

    protected void clear(MobileElement element) {
        waitForVisibility(element);
        element.clear();
    }

    protected void sendText(MobileElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    protected String getAttribute(MobileElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    protected boolean isActiveView(String viewTitleText) {
        if(shortWaitUntil(visibilityOf(viewTitle)))
            return viewTitle.getAttribute("text").equals(viewTitleText);
        else {
            LOG.warn("The view title was not visible");
            return false;
        }
    }

    protected void keyboardEnter() {
        // Java
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "done"));
    }

    protected void pickADate(String year, String month, String day) {
        Map<String, Integer> monthStr2Numb = buildMonthMap();
        click(driver.findElementById("android:id/date_picker_header_year"));
        String firstYearInList = driver.findElementsByXPath("//*[@resource-id='android:id/date_picker_year_picker']/child::node()")
                .get(0)
                .getAttribute("text");
        int diff = Integer.parseInt(firstYearInList) - Integer.parseInt(year);
        int times = diff % 7 == 0 ? diff / 7 : diff / 7 + 1;
        swipeScrollableElement(driver.findElementById("android:id/date_picker_year_picker"), Direction.DOWN, 100, times);
        click(driver.findElementsByXPath("//*[@resource-id='android:id/date_picker_year_picker']/child::node()")
                .stream()
                .filter(el -> el.getAttribute("text").equals(year))
                .collect(Collectors.toList())
                .get(0));
        String currentMonth = driver.findElementsByXPath("//*[@resource-id='android:id/month_view']/child::node()")
                .get(0)
                .getAttribute("content-desc")
                .replaceAll("[0-9 ]", "");
        if(currentMonth.equalsIgnoreCase(month)) {
            click(driver.findElementsByXPath("//*[@resource-id='android:id/month_view']/child::node()")
                    .get(Integer.parseInt(day)-1));
            return;
        }
        times = monthStr2Numb.get(removeAccent(month)) - monthStr2Numb.get(removeAccent(currentMonth));
        if(times > 0) {
            for(int i = 0; i < times; i++)
                click(driver.findElementById("android:id/next"));
            click(driver.findElementsByXPath("//*[@resource-id='android:id/month_view']/child::node()")
                    .get(Integer.parseInt(day)-1));
        }
        if(times < 0) {
            for(int i = 0; i < -(times); i++)
                click(driver.findElementById("android:id/prev"));
            click(driver.findElementsByXPath("//*[@resource-id='android:id/month_view']/child::node()")
                    .get(Integer.parseInt(day)-1));
        }
        click(driver.findElementById("android:id/button1"));
    }

    private String removeAccent(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }

    private Map<String, Integer> buildMonthMap() {
        Map<String, Integer> monthStr2Numb = new HashMap<>();
        monthStr2Numb.put("janvier", 1);
        monthStr2Numb.put("january", 1);
        monthStr2Numb.put("fevrier", 2);
        monthStr2Numb.put("february", 2);
        monthStr2Numb.put("mars", 3);
        monthStr2Numb.put("march", 3);
        monthStr2Numb.put("avril", 4);
        monthStr2Numb.put("april", 4);
        monthStr2Numb.put("mai", 5);
        monthStr2Numb.put("may", 5);
        monthStr2Numb.put("juin", 6);
        monthStr2Numb.put("june", 6);
        monthStr2Numb.put("juillet", 7);
        monthStr2Numb.put("july", 7);
        monthStr2Numb.put("aout", 8);
        monthStr2Numb.put("august", 8);
        monthStr2Numb.put("septembre", 9);
        monthStr2Numb.put("september", 9);
        monthStr2Numb.put("octobre", 10);
        monthStr2Numb.put("october", 10);
        monthStr2Numb.put("novembre", 11);
        monthStr2Numb.put("november", 11);
        monthStr2Numb.put("decembre", 12);
        monthStr2Numb.put("december", 12);
        return monthStr2Numb;
    }

}
