package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddFundsPage;
import pages.RightMenuItemsPage;
import pages.ToolbarPage;

public class AddFundsNegativeTest extends BaseTest {

    private AddFundsPage openAddFundsScreen() {
        new ToolbarPage().openRightMenu();
        new RightMenuItemsPage().openAddFunds();
        return new AddFundsPage();
    }

    @Test(description = "Verify error when amount is less than minimum ($5)")
    public void testAmountLessThanFive() {
        AddFundsPage addFunds = openAddFundsScreen();

        addFunds.enterAmount("2");

        Assert.assertTrue(addFunds.isErrorDisplayed(), "Error message should be displayed for amount < 5");
        Assert.assertFalse(addFunds.isAddFundsButtonEnabled(), "Add Funds button should be disabled for invalid amount");
    }

    @Test(description = "Verify error when amount is greater than maximum ($5000)")
    public void testAmountGreaterThanFiveThousand() {
        AddFundsPage addFunds = openAddFundsScreen();

        addFunds.enterAmount("6000");

        Assert.assertTrue(addFunds.isErrorDisplayed(), "Error message should be displayed for amount > 5000");
        Assert.assertFalse(addFunds.isAddFundsButtonEnabled(), "Add Funds button should be disabled for invalid amount");
    }

    @Test(description = "Verify error when amount field is empty")
    public void testEmptyAmount() {
        AddFundsPage addFunds = openAddFundsScreen();

        addFunds.enterAmount("");  // clear field

        Assert.assertTrue(addFunds.isErrorDisplayed(), "Error message should be displayed for empty amount");
        Assert.assertFalse(addFunds.isAddFundsButtonEnabled(), "Add Funds button should be disabled when amount is empty");
    }

    @Test(description = "Verify error when entering non-numeric characters")
    public void testNonNumericAmount() {
        AddFundsPage addFunds = openAddFundsScreen();

        addFunds.enterAmount("abc");

        Assert.assertTrue(addFunds.isErrorDisplayed(), "Error message should be displayed for non-numeric input");
        Assert.assertFalse(addFunds.isAddFundsButtonEnabled(), "Add Funds button should be disabled for non-numeric input");
    }

    @Test(description = "Verify preset buttons override invalid input")
    public void testPresetOverridesInvalidAmount() {
        AddFundsPage addFunds = openAddFundsScreen();

        addFunds.enterAmount("1");  // invalid
        Assert.assertTrue(addFunds.isErrorDisplayed());
        Assert.assertFalse(addFunds.isAddFundsButtonEnabled());

        addFunds.tapPreset20();     // valid preset

        Assert.assertFalse(addFunds.isErrorDisplayed(), "Error should disappear after selecting valid preset");
        Assert.assertTrue(addFunds.isAddFundsButtonEnabled(), "Add Funds button should be enabled after selecting preset");
    }

    @Test(description = "Verify Add Funds button does NOT open popup for invalid amount")
    public void testPopupDoesNotAppearForInvalidAmount() {
        AddFundsPage addFunds = openAddFundsScreen();

        addFunds.enterAmount("3");  // invalid

        Assert.assertFalse(addFunds.isAddFundsButtonEnabled(), "Button must be disabled");
        addFunds.clickAddFunds();   // should do nothing

        Assert.assertFalse(addFunds.isConfirmationPopupPresent(), "Popup must NOT appear for invalid amount");
    }
}
