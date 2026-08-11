package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ZoneEntryPage extends BasePage {

    private final By zoneField = By.id("com.cpa.accountManagement:id/cet_start_session_zone");
    private final By startButton = By.id("com.cpa.accountManagement:id/btn_start_session_start");
    private final By confirmYesButton = By.id("android:id/button1");

    @Step("Entering zone information to start Parking Session")
    public void enterZone(String zone) {
        log("Entering zone " + zone);
        type(zoneField, zone);
    }

    @Step("Clicking the start button")
    public void clickStart() {
        log("Clicking start button");
        click(startButton);
    }

    @Step("Confirming zone if present")
    public void confirmZoneIfPresent() {
        log("Checking for zone confirmation popup");
        if (isDisplayed(confirmYesButton, 3)) {
            log("Zone confirmation popup detected — clicking YES");
            click(confirmYesButton);
        }
    }
}
