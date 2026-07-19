package tests;

import core.BaseTest;
import core.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;
import pages.ModuleSelectorPage;
import pages.OnboardingPage;

public class LoginTest extends BaseTest {
    @Test(description = "Verify user can log in with valid credentials")
    public void testValidLogin() {

        // Read credentials from config.properties
        String email = ConfigReader.get("email");
        String password = ConfigReader.get("password");

        new OnboardingPage().handleOnboarding();
        new LandingPage().clickLogin();

        // Onboarding
        new OnboardingPage().handleOnboarding();

        // Navigate to Login
        new LandingPage().clickLogin();

        // Perform login
        LoginPage login = new LoginPage();
        login.login(email, password);

        // Validate login success
        ModuleSelectorPage module = new ModuleSelectorPage();
        Assert.assertTrue(
                module.isWelcomeScreenDisplayed(),
                "Welcome screen not displayed after login"
        );
    }

//    @Test(enabled = false, description = "Verify login fails with invalid credentials")
//    public void testInvalidLogin() {
//
//        new OnboardingPage().clickSkip();
//        new LandingPage().clickLogin();
//
//        LoginPage login = new LoginPage();
//        login.login("wrong@email.com", "wrongpass");
//
//        Assert.assertTrue(
//                login.isStillOnLoginScreen(),
//                "Login should fail but user moved to next screen"
//        );
//    }
}