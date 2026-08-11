package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static utils.ScreenshotUtils.takeScreenshot;

/* This Page handles the LOGIN button after launching the app among 2 other buttons. PAY AS GUEST and CREATE ACCOUNT Buttons*/

public class LandingPage extends BasePage {

    private final By loginButton =
            By.id("com.cpa.accountManagement:id/btn_sign_in");

    private final By landingTitle = By.id("com.cpa.accountManagement:id/tv_app_title");

    @Step("Clicking on the login button on the landing screen")
    public void clickLogin() {
        log("Clicking on Landing screen login button");
        if (isDisplayed(loginButton, 5)) {
            click(loginButton);
        } else {
            log("Login button not found — screen not ready");
            takeScreenshot("login_not_found");
        }
    }

    @Step("Checking if landing page is displayed")
    public boolean isLandingPageDisplayed() {
        return isDisplayed(loginButton);
    }

    @Step("Waiting for landing screen to be displayed")
    public void waitForLandingScreen() {
        waitForVisibility(landingTitle);
    }

}