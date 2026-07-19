package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PermissionHandlerPage extends BasePage {

    private final By allowWhileUsingAppButton =
            By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");

    private final By allowButton =
            By.id("com.android.permissioncontroller:id/permission_allow_button");

    @Step("Allowing location permission if present")
    public void allowLocationIfPresent() {
        if (isDisplayed(allowWhileUsingAppButton, 3)) {
            log("Location permission popup detected — clicking 'Allow while using app'");
            click(allowWhileUsingAppButton);
        }
    }
    @Step("Allowing notification permission if present")
    public void allowNotificationsIfPresent() {
        if (isDisplayed(allowButton, 3)) {
            log("Allowing notifications");
            click(allowButton);
        }
    }
}
