package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ActiveFriendAndEndSessionsPage;
import pages.LeftMenuItemsPage;
import pages.ParkAFriendPage;
import pages.ToolbarPage;

public class ActiveFriendSessionsTest extends BaseTest {

    @Test
    public void validateActiveSessionsUI() {

        ToolbarPage toolbar = new ToolbarPage();
        LeftMenuItemsPage menu = new LeftMenuItemsPage();
        ParkAFriendPage friendPage = new ParkAFriendPage();
        ActiveFriendAndEndSessionsPage sessions = new ActiveFriendAndEndSessionsPage();

        toolbar.openLeftMenu();
        menu.openParkAFriend();
        friendPage.openActiveSessions();

        int count = sessions.getSessionCount();
        Assert.assertTrue(count >= 0, "Active sessions count is invalid");

        if (count > 0) {
            String plate = sessions.getAllPlates().get(0);
            ActiveFriendAndEndSessionsPage.SessionDetails details = sessions.getSessionDetails(plate);

            Assert.assertNotNull(details.zone, "Zone is null or missing");
            Assert.assertNotNull(details.endTime, "End time is null or missing");
            Assert.assertNotNull(details.cost, "Cost is null or missing");
        }
    }
}
