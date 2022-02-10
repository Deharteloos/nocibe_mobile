package pageObjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ProfilePage extends Page {

    private static final Logger LOG = LogManager.getLogger(ProfilePage.class);
    private static final String VIEW_TITLE_TEXT = "PROFIL";

    @AndroidFindBy(id = "com.pictime.nocibe:id/createAccountView")
    private MobileElement createAccountBtn;

    @AndroidFindBy(id = "com.pictime.nocibe:id/gaugeView")
    private MobileElement gaugeView;

    public boolean isActiveView() {
        return isActiveView(VIEW_TITLE_TEXT);
    }

    public void navigateToCreateAccountView() {
        click(createAccountBtn);
    }

    public boolean isGaugeViewVisible() {
        return waitUntil(visibilityOf(gaugeView));
    }

}
