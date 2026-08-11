package tests;

import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class AddFundsTest extends BaseTest {

    @Test
    public void testAddFundsFlow() {

        // Open profile → Add Funds
        new ToolbarPage().openRightMenu();
        new RightMenuItemsPage().openAddFunds();

        AddFundsPage addFunds = new AddFundsPage();

//        // 1. Invalid amount (<5)
//        addFunds.enterAmount("2");
//        Assert.assertTrue(addFunds.isErrorDisplayed());
//        Assert.assertFalse(addFunds.isAddFundsButtonEnabled());
//
//        // 2. Invalid amount (>5000)
//        addFunds.enterAmount("6000");
//        Assert.assertTrue(addFunds.isErrorDisplayed());
//        Assert.assertFalse(addFunds.isAddFundsButtonEnabled());

        // 3. Valid amount
        addFunds.enterAmount("20");
        Assert.assertFalse(addFunds.isErrorDisplayed());
        Assert.assertTrue(addFunds.isAddFundsButtonEnabled());

        // 4. Click Add Funds
        addFunds.clickAddFunds();

        // 5. Confirmation popup
        Assert.assertTrue(addFunds.isConfirmationPopupPresent());
        addFunds.confirmAddFunds();

        // 6. Checkout page (webview)
        // You can detect it later if needed
        Assert.assertTrue(addFunds.isCheckoutPageLoaded(), "Checkout page did not load");

    }
}
