package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() {

        new OnboardingPage().handleOnboarding();
        new LandingPage().clickLogin();
        new LoginPage().login("ppepark1@gmail.com", "MyparkEpark13!");

        ModuleSelectorPage module = new ModuleSelectorPage();
        module.clickParkPlus();

        new PermissionHandlerPage().allowLocationIfPresent();

        StartSessionPage session = new StartSessionPage();
        session.isMapScreenDisplayed();

        LogoutPage logout = new LogoutPage();
        logout.logout();

        LandingPage landing = new LandingPage();
        Assert.assertTrue(landing.isLandingPageDisplayed());
    }
}
