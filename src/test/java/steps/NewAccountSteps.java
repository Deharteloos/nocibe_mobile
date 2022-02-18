package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.NewAccountPage;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class NewAccountSteps {

    private NewAccountPage newAccountPage;

    public NewAccountSteps(NewAccountPage newAccountPage) {
        this.newAccountPage = newAccountPage;
    }

    @Then("User must be redirected to the account creation page")
    public void userMustBeRedirectedToTheAccountCreationPage() {
        assertTrue(newAccountPage.isActiveView(), "The active view is not the account creation page");
    }

    @Given("User is on new account page view one")
    public void userIsOnNewAccountPageViewOne() {
        assertTrue(newAccountPage.isActiveView(), "The active view is not the account creation page");
        assertTrue(newAccountPage.isFirstStep(), "User is not at the first step of account creation");
    }

    @When("User fills out the form")
    public void userFillsOutTheForm(DataTable dataTable) {
        //Given that we only have one row in the table, we select directy that row
        Map<Object, Object> userData = dataTable.asMaps(String.class, String.class).get(0);
        String civility = (String) userData.get("civility");
        String firstname = (String) userData.get("firstname");
        String surname = (String) userData.get("surname");
        String email = (String) userData.get("email");
        String password = (String) userData.get("password");
        String choice = (String) userData.get("choice");
        newAccountPage.completeFirstStep(civility, firstname, surname, email, password, choice);
    }

    @And("User click on button 'Continuer'")
    public void userClickOnButtonContinuer() {
        newAccountPage.goToSecondStep();
    }

    @Then("The second view of account creation is shown")
    public void theSecondViewOfAccountCreationIsShown() {
        assertTrue(newAccountPage.isSecondStep(), "User is not at the second step of account creation");
    }

    @Given("User is on new account page view two")
    public void userIsOnNewAccountPageViewTwo() {
        assertTrue(newAccountPage.isSecondStep(), "User is not at the second step of account creation");
    }

    @When("User fills in his contact details {string} {string} {int}")
    public void userFillsInHisContactDetails(String address, String country, Integer zipcode) {
        newAccountPage.fillAddressField(address);
        newAccountPage.fillZipcodeField(zipcode);
    }
    
    @And("User fills in mobile number field {string}")
    public void userFillsInMobileNumberField(String phone) {
        newAccountPage.fillPhoneField(phone);
    }

    @And("User select his date of birth {string} {string} {string}")
    public void userSelectHisDateOfBirth(String year, String month, String day) {
        newAccountPage.setBirthDate(year, month, day);
    }

    @And("User click on button 'Créer un compte'")
    public void userClickOnButtonCreerUnCompte() {
        newAccountPage.goToSecondStep();
    }

    @When("User choose civility {string}")
    public void userChooseCivility(String civility) {
        newAccountPage.selectCivility(civility);
    }


    @And("User fills out fields {string} and {string}")
    public void userFillsOutFieldsAnd(String firstname, String surname) {
        newAccountPage.fillFirstnameField(firstname);
        newAccountPage.fillSurnameField(surname);
    }

    @And("User fills out credentials fields {string} and {string}")
    public void userFillsOutCredentialsFieldsAnd(String email, String password) {
        newAccountPage.fillEmailField(email);
        newAccountPage.fillPasswordField(password);
    }

    @And("On field nocibe loyalty card user click on {string}")
    public void onFieldNocibeLoyaltyCardUserClickOn(String choice) {
        newAccountPage.chooseFidelity(choice);
    }

    @When("User scrolls to bottom of the page without filling any fields")
    public void userScrollsToBottomOfThePageWithoutFillingAnyFields() {
        newAccountPage.scrollToTheBottom();
    }

    @Then("User should see button 'Continuer' disabled")
    public void userShouldSeeButtonContinuerDisabled() {
        assertFalse(newAccountPage.isActionButtonEnabled(), "The button 'Continuer' is enabled");
    }

    @When("User fills email field {string}")
    public void userFillsEmailField(String email) {
        newAccountPage.fillEmailField(email);
    }

    @Then("User should see error message for email field")
    public void userShouldSeeErrorMessageForEmailField() {
        assertTrue(newAccountPage.isEmailErrorTextVisible(), "No error message was shown for the email field.");
    }

    @When("User fills password field {string}")
    public void userFillsPasswordField(String password) {
        newAccountPage.fillPasswordField(password);
    }

    @Then("User should see error message for password field")
    public void userShouldSeeErrorMessageForPasswordField() {
        assertTrue(newAccountPage.isPasswordErrorTextVisible(), "No error message was shown for the password field");
    }

    @When("User scrolls to the bottom of the page")
    public void userScrollsToTheBottomOfThePage() {
        newAccountPage.scrollToTheBottom();
    }

    @And("User select {string} On the field 'Avez-vous une carte de fidélité Nocibé ?'")
    public void userSelectOnTheFieldAvezVousUneCarteDeFideliteNocibe(String choice) {
        newAccountPage.chooseFidelity(choice);
    }

    @Then("User should be able to enter fidelity card number")
    public void userShouldBeAbleToEnterFidelityCardNumber() {
        assertTrue(newAccountPage.isFidelityCardFieldVisible(), "The field of the fidelity card number did not appear.");
    }

    @And("User click on discover password")
    public void userClickOnDiscoverPassword() {
        newAccountPage.clickOnShowHidePassword();
    }

    @Then("Password must be visible")
    public void passwordMustBeVisible() {

    }
}
