package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ToolbarPage extends BasePage {

    // Left hamburger menu
    private final By leftMenuButton =
            By.xpath("//*[@content-desc='Drawer Open']");

    // Right profile/settings icon
    private final By rightMenuButton =
            By.id("com.cpa.accountManagement:id/action_openRight");

    // Title text
    private final By titleText =
            By.id("com.cpa.accountManagement:id/tv_app_title");
    @Step("Opening the left menu")
    public void openLeftMenu() {
        log("Opening left menu");
        click(leftMenuButton);
    }

    @Step("Opening the right menu")
    public void openRightMenu() {
        log("Opening right menu");
        click(rightMenuButton);
    }

    @Step("Getting the title text")
    public String getTitle() {
        log("Getting title text");
        return getText(titleText);
    }
    @Step("Checking if the right menu is visible")
    public boolean isRightMenuVisible() {
        log("Checking if right menu is visible");
        return isDisplayed(By.id("com.cpa.accountManagement:id/action_openRight"));
    }

}
