package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ActiveFriendAndEndSessionsPage;
import pages.LeftMenuItemsPage;
import pages.ParkAFriendPage;
import pages.ToolbarPage;

public class EndActiveFriendSessionsTest extends BaseTest {

    @Test
    public void endAllActiveSessions() {

        ToolbarPage toolbar = new ToolbarPage();
        LeftMenuItemsPage menu = new LeftMenuItemsPage();
        ParkAFriendPage friendPage = new ParkAFriendPage();
        ActiveFriendAndEndSessionsPage sessions = new ActiveFriendAndEndSessionsPage();

        toolbar.openLeftMenu();
        menu.openParkAFriend();
        friendPage.openActiveSessions();
        sessions.endAllSessions();

        int count = sessions.getSessionCount();
        System.out.println("[LOG] Final session count = " + count);

// If sessions existed, they should now be 0
// If no sessions existed, count was already 0
        Assert.assertEquals(count, 0, "Some sessions were not ended");

    }
}
