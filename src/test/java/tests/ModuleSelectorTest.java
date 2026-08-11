package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ModuleSelectorTest extends BaseTest {

    @Test
    public void testNavigateToSessionScreen() {

        new OnboardingPage().handleOnboarding();
        new LandingPage().clickLogin();
        new LoginPage().login("ppepark1@gmail.com", "MyparkEpark13!");

        ModuleSelectorPage module = new ModuleSelectorPage();
        Assert.assertTrue(module.isWelcomeScreenDisplayed());

        module.clickParkPlus();

        PermissionHandlerPage permission = new PermissionHandlerPage();
        permission.allowLocationIfPresent();

        StartSessionPage session = new StartSessionPage();
        Assert.assertTrue(session.isMapScreenDisplayed());
    }
}
