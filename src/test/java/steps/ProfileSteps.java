package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ProfilePage;

import static org.testng.Assert.assertTrue;

public class ProfileSteps {

    private ProfilePage profilePage;

    public ProfileSteps(ProfilePage profilePage) {
        this.profilePage = profilePage;
    }

    @And("User click on the button {string}")
    public void userClickOnTheButtonJeCreeUnCompte(String str0) {
        profilePage.navigateToCreateAccountView();
    }

    @Then("User must be redirected to his personal account space")
    public void userMustBeRedirectedToHisPersonalAccountSpace() {
        assertTrue(profilePage.isGaugeViewVisible(), "The user is not connected. The loyalty points are not visible in the profile page.");
    }
}
