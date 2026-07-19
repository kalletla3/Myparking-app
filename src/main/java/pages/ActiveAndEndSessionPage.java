package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ActiveAndEndSessionPage extends BasePage {

    private final By remainingTime = By.xpath("//*[contains(@text,'Remaining')]");
    private final By endSessionButton = By.id("com.cpa.accountManagement:id/btn_end_parking_session");
    private final By activeSessionTitle = By.id("com.cpa.accountManagement:id/tv_app_title");
    private final By alertTitle = By.id("com.cpa.accountManagement:id/alertTitle");
    private final By popupMessage = By.id("android:id/message");
    private final By popUpActionYes = By.id("android:id/button1");
    private final By popUpActionCancel = By.id("android:id/button2");

    //This page has both Active session and End session functionalities, so we will handle both here.
    @Step("Waiting for the active session screen")
    public void waitForActiveSessionScreen() {
        waitForVisibility(remainingTime);
    }

    //Verifying if End Session button is displayed
    @Step("Checking if End Session button is displayed")
    public boolean isEndSessionDisplayed() {
        return isDisplayed(endSessionButton, 5);
    }

    //Ending the session
    @Step("Clicking the end session button")
    public void clickEndSession() {
        log("Clicking End Session button");
        click(endSessionButton);
        System.out.println("[`DEBUG]Clicked on End Session button");
    }

    // Wait for the first popup (YES)
    @Step("Waiting for End Session confirmation popup")
    public void waitForEndSessionPopup() {
        log("Waiting for End Session confirmation popup");
        waitForVisibility(alertTitle, 10);
    }

    // Click YES on the first popup
    @Step("Confirming End Session")
    public void confirmEndSession() {
        log("Confirming End Session (YES)");
        click(popUpActionYes);
    }

    // Handle the second popup (OK) button after Ending the session
    @Step("Confirming End Session OK")
    public void confirmEndSessionOkIfPresent() {
        if (isDisplayed(By.id("android:id/button1"), 3)) {
            log("End Session OK popup detected — clicking OK");
            click(By.id("android:id/button1"));
            log("OK button clicked");
        }
    }


}
