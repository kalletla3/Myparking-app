//package tests;
//
//import core.BaseTest;
//import core.ConfigReader;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import pages.ActiveAndEndSessionPage;
//import pages.PermissionHandlerPage;
//import pages.StartSessionPage;
//import pages.ZoneEntryPage;
//
//public class StartAndEndSessionTest extends BaseTest {
//
//    private String zoneNumber;
//    private StartSessionPage session;
//
//    @Test(description = "Start a parking session")
//    public void startSession() {
//
//        // Read zone from config.properties
//        zoneNumber = ConfigReader.get("zone1604");
//        session = new StartSessionPage();
//        session.isMapScreenDisplayed();
//        session.clickStartParkingSession();
//
//        // Allow notifications if popup appears
//        new PermissionHandlerPage().allowNotificationsIfPresent();
//
//        // Enter zone details
//        ZoneEntryPage zone = new ZoneEntryPage();
//        zone.enterZone(zoneNumber);
//        zone.clickStart();
//        zone.confirmZoneIfPresent();  // YES popup here
//
//        // Validate active session screen
//        ActiveAndEndSessionPage active = new ActiveAndEndSessionPage();
//        active.waitForActiveSessionScreen();
//
//        Assert.assertTrue(
//                active.isEndSessionDisplayed(),
//                "End Session button not displayed after starting session"
//        );
//    }
//
//    @Test(description = "End a parking session", dependsOnMethods = "startSession")
//    public void endSession() {
//        ActiveAndEndSessionPage active = new ActiveAndEndSessionPage();
//
//        // End session
//        active.clickEndSession();
//        active.confirmEndSessionOkIfPresent();   // OK popup here
//
//        // Validate return to Start Session screen
//        Assert.assertTrue(
//                session.isMapScreenDisplayed(),
//                "Map screen not displayed after ending session"
//        );
//        // Allow notifications if popup appears
//        new PermissionHandlerPage().allowLocationIfPresent();
//    }
//}
package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class StartAndEndSessionTest extends BaseTest {

    @Test
    public void testStartAndEndParkingSession() {

        // Start session
        StartSessionPage session = new StartSessionPage();
        session.isMapScreenDisplayed();

        StartSessionPage start = new StartSessionPage();
        start.clickStartParkingSession();

        new PermissionHandlerPage().allowNotificationsIfPresent();

        ZoneEntryPage zone = new ZoneEntryPage();
        zone.enterZone("1604");
        zone.clickStart();
        zone.confirmZoneIfPresent();

        // Active session
        ActiveAndEndSessionPage active = new ActiveAndEndSessionPage();
        active.waitForActiveSessionScreen();
        Assert.assertTrue(active.isEndSessionDisplayed());

        // End session
        active.clickEndSession();
        active.confirmEndSessionOkIfPresent();

        // After ending, you should be back on Start Session screen
        Assert.assertTrue(session.isMapScreenDisplayed());
    }

}