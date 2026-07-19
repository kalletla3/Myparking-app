package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

//This is a Module Selection Screen to either Select PP or RPP
public class ModuleSelectorPage extends BasePage {

    private final By welcomeTitle =
            By.xpath("//*[@text='WELCOME TO MYPARKING']");

    private final By parkPlusButton =
            By.id("com.cpa.accountManagement:id/moduleSelector_btn_parkPlus");

    public boolean isWelcomeScreenDisplayed() {
        return isDisplayed(welcomeTitle);
    }

   //Selecting PARKPLUS option from the Product Selection Screen
   @Step("Selecting PARKPLUS option from the product selection page")
    public void clickParkPlus() {
        log("Clicking PARKPLUS option from the Product Selection Screen");
        click(parkPlusButton);
    }
}