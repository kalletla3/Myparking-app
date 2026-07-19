package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class EndSessionTest extends BaseTest {

    private void handleAnyPopupIfPresent() {
        try {
            PermissionHandlerPage permission = new PermissionHandlerPage();
            permission.allowLocationIfPresent();        // "While using the app"
            permission.allowNotificationsIfPresent();   // "Allow notifications"

            ActiveAndEndSessionPage end = new ActiveAndEndSessionPage();
            end.confirmEndSessionOkIfPresent();         // App's OK popup
        } catch (Exception ignored) {}
    }

    @Test
    public void testEndParkingSession() {

        ActiveAndEndSessionPage active = new ActiveAndEndSessionPage();
        StartSessionPage startSession = new StartSessionPage();

        // Wait for active session screen
        active.waitForActiveSessionScreen();
        Assert.assertTrue(active.isEndSessionDisplayed(), "End Session button not displayed");

        // Click End Session
        active.clickEndSession();

        // Handle ANY popup that appears BEFORE the OK popup
        handleAnyPopupIfPresent();

        // Now wait for the End Session OK popup
        active.waitForEndSessionPopup();

        // Click OK on End Session popup
        active.confirmEndSessionOkIfPresent();

        // Validate return to Start Session screen
        Assert.assertTrue(
                startSession.isMapScreenDisplayed(),
                "Map screen not displayed after ending session"
        );
    }
}
