package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class StartSessionTest extends BaseTest {

    @Test
    public void testStartParkingSession() {

        // Start session screen
        StartSessionPage session = new StartSessionPage();
        session.isMapScreenDisplayed();

        // Click Start Parking Session
        StartSessionPage start = new StartSessionPage();
        start.clickStartParkingSession();

        // Allow notifications if popup appears
        new PermissionHandlerPage().allowNotificationsIfPresent();

        // Enter zone
        ZoneEntryPage zone = new ZoneEntryPage();
        zone.enterZone("1604");
        zone.clickStart();
        zone.confirmZoneIfPresent();

        // Active session screen
        ActiveAndEndSessionPage active = new ActiveAndEndSessionPage();
        active.waitForActiveSessionScreen();
        Assert.assertTrue(active.isEndSessionDisplayed(), "End Session button not displayed");
    }
}
