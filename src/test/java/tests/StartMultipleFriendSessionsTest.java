package tests;

import core.BaseTest;
import core.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LeftMenuItemsPage;
import pages.ParkAFriendPage;
import pages.ToolbarPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartMultipleFriendSessionsTest extends BaseTest {

    public static List<String> multiplePlates = new ArrayList<>();

    @Test
    public void startMultipleSessions() {

        // Read zones from config.properties
        List<String> zones = Arrays.asList(
                ConfigReader.get("zone1604"),
                ConfigReader.get("zone2442"),
                ConfigReader.get("zone1008")
        );

        ToolbarPage toolbar = new ToolbarPage();
        LeftMenuItemsPage menu = new LeftMenuItemsPage();
        ParkAFriendPage friendPage = new ParkAFriendPage();

        toolbar.openLeftMenu();
        menu.openParkAFriend();

        // Start 3 sessions using random allowed zones from config
        multiplePlates = friendPage.startMultipleFriendSessionsRandomZones(zones, 3);

        Assert.assertTrue(
                multiplePlates.size() > 0,
                "Multiple sessions not created"
        );
    }
}
