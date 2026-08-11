package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class AddFundsPage extends BasePage {

    // Amount field
    private final By amountField = By.id("com.cpa.accountManagement:id/editAmount");

    // Error message
    private final By errorLabel = By.id("com.cpa.accountManagement:id/amountErrorLabel");

    // Preset buttons
    private final By btn5 = By.id("com.cpa.accountManagement:id/btn5");
    private final By btn10 = By.id("com.cpa.accountManagement:id/btn10");
    private final By btn20 = By.id("com.cpa.accountManagement:id/btn20");
    private final By btn50 = By.id("com.cpa.accountManagement:id/btn50");
    private final By btn100 = By.id("com.cpa.accountManagement:id/btn100");

    // Add Funds button
    private final By addFundsButton = By.id("com.cpa.accountManagement:id/btn_add_funds");

    // Confirmation popup
    private final By popupMessage = By.id("android:id/message");
    private final By okButton = By.id("android:id/button1");
    private final By cancelButton = By.id("android:id/button2");

    // -----------------------------
    // ACTIONS
    // -----------------------------

    @Step("Entering amount: {amount}")
    public void enterAmount(String amount) {
        log("Entering amount: " + amount);
        clearAndType(amountField, amount);
    }

    @Step("Checking if error message is displayed")
    public boolean isErrorDisplayed() {
        return isDisplayed(errorLabel, 2);
    }

    @Step("Getting error message text")
    public String getErrorMessage() {
        return getText(errorLabel);
    }

    @Step("Checking if Add Funds button is enabled")
    public boolean isAddFundsButtonEnabled() {
        return isEnabled(addFundsButton);
    }

    @Step("Clicking Add Funds button")
    public void clickAddFunds() {
        log("Clicking Add Funds button");
        click(addFundsButton);
    }

    // -----------------------------
    // PRESET BUTTONS
    // -----------------------------

    public void tapPreset5() { click(btn5); }
    public void tapPreset10() { click(btn10); }
    public void tapPreset20() { click(btn20); }
    public void tapPreset50() { click(btn50); }
    public void tapPreset100() { click(btn100); }

    // -----------------------------
    // POPUP HANDLING (same style as ZoneEntryPage)
    // -----------------------------

    @Step("Checking if Add Funds confirmation popup is present")
    public boolean isConfirmationPopupPresent() {
        return isDisplayed(popupMessage, 3);
    }

    @Step("Confirming Add Funds (OK)")
    public void confirmAddFunds() {
        if (isDisplayed(okButton, 3)) {
            log("Add Funds popup detected — clicking OK");
            click(okButton);
        }
    }

    @Step("Cancelling Add Funds (CANCEL)")
    public void cancelAddFunds() {
        if (isDisplayed(cancelButton, 3)) {
            log("Add Funds popup detected — clicking CANCEL");
            click(cancelButton);
        }
    }

    public boolean isCheckoutPageLoaded() {
        log("Checkout page loaded successfully (secure WebView detected)");
        return isDisplayed(By.className("android.webkit.WebView"), 5);
    }

}
