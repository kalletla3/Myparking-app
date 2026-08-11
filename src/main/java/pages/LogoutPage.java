package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LogoutPage extends BasePage {

    private final By profileIcon =
            By.xpath("//*[@content-desc='Settings']");

    private final By signOutButton =
            By.xpath("//*[@text='Sign Out']");

    @Step("Logging out of the application")
    public void logout() {
        log("Clicking on profile icon");
        click(profileIcon);
        log("Clicking on sign out");
        click(signOutButton);
    }
}
