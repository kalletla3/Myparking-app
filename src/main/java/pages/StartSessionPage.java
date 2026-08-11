package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class StartSessionPage extends BasePage {

    private final By sessionTitle =
            By.id("com.cpa.accountManagement:id/tv_app_title");

    private final By waitForMap =
            By.id("com.cpa.accountManagement:id/ll_map_view_container");
   // or we can use this: com.cpa.accountManagement:id/ll_map_view_container

    private final By startParkingSessionButton =
            By.id("com.cpa.accountManagement:id/btn_start_parking_session");

    //Start the actual session
    @Step("Clicking the start parking session button")
    public void clickStartParkingSession() {
        log("Clicking on start parking session button");
        click(startParkingSessionButton);
    }

    //Wait for the map container to be visible
    @Step("Checking if the map screen is displayed")
    public boolean isMapScreenDisplayed() {
        log("Waiting for the map to be displayed on the session screen");
        return isDisplayed(waitForMap, 5);
    }

}
