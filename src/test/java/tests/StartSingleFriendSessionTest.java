package tests;

import core.BaseTest;
import core.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LeftMenuItemsPage;
import pages.ParkAFriendPage;
import pages.ToolbarPage;

public class StartSingleFriendSessionTest extends BaseTest {

    public static String singlePlate;

    @Test
    public void startSingleSession() {
        // Read zone from config.properties
        String zoneNumber = ConfigReader.get("zone1604");


        ToolbarPage toolbar = new ToolbarPage();
        LeftMenuItemsPage menu = new LeftMenuItemsPage();
        ParkAFriendPage friendPage = new ParkAFriendPage();

        toolbar.openLeftMenu();
        menu.openParkAFriend();

        singlePlate = friendPage.startSingleFriendSession(zoneNumber);

        Assert.assertNotNull(singlePlate, "Single session plate was not created");
    }
}
