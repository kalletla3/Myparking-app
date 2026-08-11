package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class EndToEndTest extends BaseTest {

    @Test
    public void testFullEndToEndFlow() {

        // 1. Onboarding
        OnboardingPage onboarding = new OnboardingPage();
        onboarding.handleOnboarding();

        // 2. Landing Page → Login
        LandingPage landing = new LandingPage();
        landing.clickLogin();

        // 3. Login
        LoginPage login = new LoginPage();
        login.login("ppepark1@gmail.com", "MyparkEpark13!");

        // 4. Module Selector
        ModuleSelectorPage module = new ModuleSelectorPage();
        Assert.assertTrue(module.isWelcomeScreenDisplayed(), "Login failed — Welcome screen not displayed thats why Module Selector screen is not displayed");
        module.clickParkPlus();

        // 5. Location Permission
        PermissionHandlerPage locationPermission = new PermissionHandlerPage();
        locationPermission.allowLocationIfPresent();

        // 6. Session Screen
        StartSessionPage session = new StartSessionPage();
        session.isMapScreenDisplayed();
        Assert.assertTrue(session.isMapScreenDisplayed(), "Session screen not displayed");

        // 7. Start Parking Session
        session.clickStartParkingSession();

        // 8. Notification Permission
        PermissionHandlerPage permissionPage = new PermissionHandlerPage();
        permissionPage.allowNotificationsIfPresent();

        // 9. Enter Zone
        ZoneEntryPage zoneEntry = new ZoneEntryPage();
        zoneEntry.enterZone("1604");
        zoneEntry.clickStart();

        // 10. Confirm Zone
        zoneEntry.confirmZoneIfPresent();

        // 11. Validate Active Session
        ActiveAndEndSessionPage activeSession = new ActiveAndEndSessionPage();
        Assert.assertTrue(activeSession.isEndSessionDisplayed(), "End Session button not visible");

        // 12. Stop the session
        activeSession.clickEndSession();

        // 13. Handle "Session Ended" popup
        activeSession.isEndSessionDisplayed();

        // 14. Validate we returned to Session screen
        StartSessionPage sessionAfterEnd = new StartSessionPage();
        Assert.assertTrue(
                sessionAfterEnd.isMapScreenDisplayed(),
                "Did not return to START/END SESSION screen after ending session"
        );

        // 15. Logout
        LogoutPage logout = new LogoutPage();
        logout.logout();

        // 16. Validate Landing Page
        Assert.assertTrue(
                landing.isLandingPageDisplayed(),
                "Landing page not displayed after logout"
        );
    }
}
