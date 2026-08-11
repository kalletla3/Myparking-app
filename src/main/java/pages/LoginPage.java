package pages;

import core.BasePage;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

/* This Page handles the login functionality after clicking the LOGIN button on the Landing Screen */

public class LoginPage extends BasePage {

    private final By emailField  = By.id("com.cpa.accountManagement:id/cet_login_email");
    private final By passwordField  = By.id("com.cpa.accountManagement:id/cet_login_password");
    private final By loginBtn = By.id("com.cpa.accountManagement:id/btn_sign_in_server");

    @Step("Logging in with email and password")
    public void login(String email, String password) {
        log("Entering email: " + email);
        type(emailField, email);
        log("Entering password: " + password);
        type(passwordField, password);
        log("Clicking login button");
        click(loginBtn);
    }
    @Step("Checking if still on login screen")
    public boolean isStillOnLoginScreen() {
        return isDisplayed(loginBtn);
    }
}
