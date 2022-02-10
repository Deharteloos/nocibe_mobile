package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.ProfilePage;

import static org.testng.Assert.assertTrue;

public class HomeSteps {

    private HomePage homePage;
    private ProfilePage profilePage;

    public HomeSteps(HomePage homePage, ProfilePage profilePage) {
        this.homePage = homePage;
        this.profilePage = profilePage;
    }

    @Given("User is on homepage")
    public void userIsOnHomepage() {
        homePage.acceptsCookie();
        homePage.skipNews();
        assertTrue(homePage.isHomeNavigationSelected(), "The active view is not the homepage");
    }

    @When("User clicks on profile icon")
    public void userClicksOnProfileIcon() {
        homePage.navigateToProfileView();
        assertTrue(profilePage.isActiveView(), "The active view is not the profile view");
    }

}
