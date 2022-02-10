package pageObjects;

import enums.Direction;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class NewAccountPage extends Page {

    private static final Logger LOG = LogManager.getLogger(NewAccountPage.class);
    private static final String VIEW_TITLE_TEXT = "NOUVEAU COMPTE";

    @AndroidFindBy(id = "com.pictime.nocibe:id/titleTextView")
    private List<MobileElement> viewTitleLists;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_profile_gender_item']/android.widget.RadioGroup/child::node()")
    private List<MobileElement> civilities;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_email_editor']/child::node()[@resource-id='com.pictime.nocibe:id/editText']")
    private MobileElement emailField;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_password_editor']/child::node()[@resource-id='com.pictime.nocibe:id/editText']")
    private MobileElement passwordField;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_profile_fidelity_item']/android.widget.RadioGroup/child::node()")
    private List<MobileElement> fidelityChoices;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_text_editor'][2]/child::node()")
    private MobileElement nameField;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_text_editor'][1]/child::node()")
    private MobileElement firstnameField;

    @AndroidFindBy(id = "com.pictime.nocibe:id/actionButton")
    private MobileElement continueButton;

    @AndroidFindBy(id = "com.pictime.nocibe:id/item_profile_creation_title")
    private MobileElement stepTwoFirstSection;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_street_editor']/child::node()")
    private MobileElement addressField;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_countries']/child::node()[@resource-id='com.pictime.nocibe:id/selectionTextView']")
    private MobileElement countrySelect;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_number_editor']/child::node()")
    private MobileElement zipcodeField;

    @AndroidFindBy(id = "com.pictime.nocibe:id/phoneEditText")
    private MobileElement phoneNumberField;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_profile_creation_date']/child::node()[@resource-id='com.pictime.nocibe:id/selectionTextView']")
    private MobileElement dateSelect;

    @AndroidFindBy(id = "android:id/date_picker_header_year")
    private MobileElement dateYearPicker;

    @AndroidFindBy(id = "android:id/datePicker")
    private MobileElement datePicker;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_email_editor']/child::node()[@resource-id='com.pictime.nocibe:id/errorTextView']")
    private MobileElement emailErrorText;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_password_editor']/child::node()[@resource-id='com.pictime.nocibe:id/errorTextView']")
    private MobileElement passwordErrorText;

    @AndroidFindBy(xpath = "//*[@resource-id='com.pictime.nocibe:id/item_profile_fidelity_item']/android.widget.EditText")
    private MobileElement fidelityCardField;

    public boolean isActiveView() {
        return isActiveView(VIEW_TITLE_TEXT);
    }

    public boolean isFirstStep() {
        waitUntil(visibilityOf(viewTitleLists.get(0)));
        return viewTitleLists.stream().anyMatch(el -> el.getAttribute("text").equals("Bienvenue!"));
    }

    public void fillSurnameField(String surname) {
        set(nameField, surname);
    }

    public void fillFirstnameField(String firstname) {
        set(firstnameField, firstname);
    }

    public void fillEmailField(String email) {
        click(emailField);
        set(emailField, email);
        keyboardEnter();
    }

    public void fillPasswordField(String password) {
        click(passwordField);
        set(passwordField, password);
        keyboardEnter();
    }

    public void selectCivility(String civility) {
        click(civilities.stream()
                .filter(el -> el.getAttribute("text").equals(civility))
                .collect(Collectors.toList())
                .get(0));
    }

    public void chooseFidelity(String choice) {
        swipeScreen(Direction.UP, 25);
        click(fidelityChoices.stream()
                .filter(el -> el.getAttribute("text").equals(choice))
                .collect(Collectors.toList())
                .get(0));
    }

    public void completeFirstStep(String civility, String firstname, String surname, String email,String password, String choice) {
        selectCivility(civility);
        fillFirstnameField(firstname);
        fillSurnameField(surname);
        fillEmailField(email);
        fillPasswordField(password);
        chooseFidelity(choice);
    }

    public void goToSecondStep() {
        if(shortWaitUntil(attributeContains(continueButton, "enabled", "true"))) {
            click(continueButton);
        }
        else LOG.warn("The button continue wasn't active");
    }

    public boolean isSecondStep() {
        return longWaitUntil(visibilityOf(stepTwoFirstSection));
    }

    public void fillAddressField(String address) {
        set(addressField, address);
    }

    public void fillZipcodeField(Integer zipcode) {
        set(zipcodeField, zipcode.toString());
    }

    public void fillPhoneField(String phone) {
        set(phoneNumberField, phone);
        swipe(Direction.UP);
    }

    public void setBirthDate(String year, String month, String day) {
        click(dateSelect);
        if(!longWaitUntil(visibilityOf(dateYearPicker)))
            LOG.warn("There was no DatePicker found");
        else pickADate(year, month, day);
        longWaitUntil(attributeContains(continueButton, "enabled", "true"));
    }

    public void scrollToTheBottom() {
        swipe(Direction.UP);
    }

    public boolean isActionButtonEnabled() {
        return shortWaitUntil(attributeContains(continueButton, "enabled", "true"));
    }

    public boolean isEmailErrorTextVisible() {
        return shortWaitUntil(visibilityOf(emailErrorText));
    }

    public boolean isPasswordErrorTextVisible() {
        return shortWaitUntil(visibilityOf(passwordErrorText));
    }

    public boolean isFidelityCardFieldVisible() {
        return waitUntil(visibilityOf(fidelityCardField));
    }

    public void clickOnShowHidePassword() {
        Point pwdFieldPosition = passwordField.getLocation();
        Dimension pwdFieldDimension = passwordField.getSize();
        int showPwdBtn_X, showPwdBtn_Y;
        showPwdBtn_X = (int)(pwdFieldDimension.width * 0.97) + pwdFieldPosition.x;
        showPwdBtn_Y = pwdFieldDimension.height / 2 + pwdFieldPosition.y;
        touchAction.tap(PointOption.point(showPwdBtn_X, showPwdBtn_Y))
                .perform();
    }

}
