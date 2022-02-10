package pageObjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class HomePage extends Page {

    private static final Logger LOG = LogManager.getLogger(HomePage.class);

    @AndroidFindBy(id = "com.pictime.nocibe:id/button_agree")
    private MobileElement agreeButton;

    @AndroidFindBy(id = "com.pictime.nocibe:id/skip")
    private MobileElement skipButton;

    @AndroidFindBy(id = "com.pictime.nocibe:id/nextButton")
    private MobileElement nextButton;

    @AndroidFindBy(accessibility = "Profil")
    private MobileElement profileNavigationBtn;

    @AndroidFindBy(accessibility = "Accueil")
    private MobileElement homeNavigationBtn;

    public boolean isHomeNavigationSelected() {
        if(!shortWaitUntil(visibilityOf(homeNavigationBtn)))
            LOG.warn("Home Navigation is not visible");
        return homeNavigationBtn.isSelected();
    }

    public void acceptsCookie() {
        //if(!waitForVisibility(agreeButton))
        if(!loadingWaitUntil(visibilityOf(agreeButton)))
            LOG.info("The cookies dialog box was not shown");
        else click(agreeButton);
    }

    public void skipNews() {
        if(!waitForVisibility(skipButton))
            LOG.info("There was no news");
        else click(skipButton);
    }

    public void navigateToProfileView() {
        click(profileNavigationBtn);
    }

}
