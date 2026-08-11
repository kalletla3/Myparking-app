package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RightMenuItemsPage extends BasePage {

    private final By addFundsOption = By.xpath("//android.widget.CheckedTextView[@text='Add Funds']");

    @Step("Opening Add Funds screen from right menu")
    public void openAddFunds() {
        log("Clicking Add Funds option");
        click(addFundsOption);
    }
}
