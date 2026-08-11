package pages;

import core.BasePage;
import org.openqa.selenium.By;

public class LeftMenuItemsPage extends BasePage {

    private final By startEndSessionOption =
            By.id("com.cpa.accountManagement:id/parking_session");

    private final By findParkingOption =
            By.id("com.cpa.accountManagement:id/find_parking");

    private final By parkAFriendOption =
            By.id("com.cpa.accountManagement:id/park_a_friend");

    private final By payAsGuestOption =
            By.id("com.cpa.accountManagement:id/ppLeftMenu_menuItem_guestPay");

    private final By localDealsOption =
            By.id("com.cpa.accountManagement:id/ppLeftMenu_menuItem_localDeals");

    private final By residentialParkingOption =
            By.id("com.cpa.accountManagement:id/authParing_menuItem_rpp");

    private final By faqOption =
            By.id("com.cpa.accountManagement:id/faq");

    private final By contactUsOption =
            By.id("com.cpa.accountManagement:id/contact_us");

    public void openStartEndSession() {
        log("Select or click on start/end session Menu option");
        click(startEndSessionOption);
    }
    public void openFindParking() {
        log("Select or click on find parking Menu option");
        click(findParkingOption);
    }
    public void openParkAFriend() {
        log("Select or click on park a friend Menu option");
        click(parkAFriendOption);
    }
    public void openPayAsGuest() {
        log("Select or click on pay as guest Menu option");
        click(payAsGuestOption);
    }
    public void openLocalDeals() {
        log("Select or click on local deals Menu option");
        click(localDealsOption);
    }
    public void openResidentialParking() {
        log("Select or click on residential parking Menu option");
        click(residentialParkingOption);
    }
    public void openFAQ() {
        log("Select or click on FAQ Menu option");
        click(faqOption);
    }
    public void openContactUs() {
        log("Select or click on contact us Menu option");
        click(contactUsOption);
    }
}
