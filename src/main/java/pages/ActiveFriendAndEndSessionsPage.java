package pages;

import core.BasePage;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ActiveFriendAndEndSessionsPage extends BasePage {

    private final By plateLabel =
            By.id("com.cpa.accountManagement:id/tv_active_friend_session_plate");

    private final By deleteButton =
            By.id("com.cpa.accountManagement:id/img_btn_active_friend_session_delete");

    private final By toggleButton =
            By.id("com.cpa.accountManagement:id/iv_active_friend_session_toggle_icon");

    private final By endTimeLabel =
            By.id("com.cpa.accountManagement:id/tv_active_friend_session_end_time");

    private final By zoneLabel =
            By.id("com.cpa.accountManagement:id/tv_active_friend_session_zone_number");

    private final By costLabel =
            By.id("com.cpa.accountManagement:id/tv_active_friend_session_cost");

    // Dialog box locators
    private final By stopDialogTitle =
            By.id("com.cpa.accountManagement:id/alertTitle");
    private final By stopDialogMessage =
            By.id("android:id/message");
    private final By stopDialogYes =
            By.id("android:id/button1");
    private final By stopDialogCancel =
            By.id("android:id/button2");


    //  BASIC LIST OPERATIONS

    public int getSessionCount() {
        return findElements(plateLabel).size();
    }

    public List<String> getAllPlates() {
        List<String> plates = new ArrayList<>();
        for (WebElement el : findElements(plateLabel)) {
            plates.add(el.getText().trim());
        }
        return plates;
    }

    public boolean isPlatePresent(String plate) {
        return getAllPlates().contains(plate.trim());
    }


    //  ROW LOOKUP (FIXED VERSION)

    /**
     * Finds the entire session row for a given plate.
     * Uses ancestor lookup instead of fragile "./..".
     */
    private WebElement getRowByPlate(String plate) {

        scrollToPlate(plate);

        String xpath =
                "//android.widget.TextView[@resource-id='com.cpa.accountManagement:id/tv_active_friend_session_plate' " +
                        "and @text='" + plate + "']" +
                        "/ancestor::android.widget.RelativeLayout[1]";


        return findElement(By.xpath(xpath));
    }

    //  DTO

    public static class SessionDetails {
        public final String plate;
        public final String endTime;
        public final String zone;
        public final String cost;

        public SessionDetails(String plate, String endTime, String zone, String cost) {
            this.plate = plate;
            this.endTime = endTime;
            this.zone = zone;
            this.cost = cost;
        }
    }

    private void scrollList() {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector()" +
                            ".resourceId(\"com.cpa.accountManagement:id/rv_active_friend_session_details\"))" +
                            ".scrollForward();"
            ));
        } catch (Exception e) {
            System.out.println("[LOG] No more scroll inside list");
        }
    }

    private void scrollListDown() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"com.cpa.accountManagement:id/rv_active_friend_session_details\")).scrollForward();"
        ));
    }

    private void scrollListUp() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"com.cpa.accountManagement:id/rv_active_friend_session_details\")).scrollBackward();"
        ));
    }


    private void tapDeleteButtonExact() {
        tapByCoordinates(705, 210);
    }

    public void waitForAtLeastOneSession() {
        for (int i = 0; i < 10; i++) {
            if (getSessionCount() > 0) return;
            wait(500);
        }
    }

    /**
     * Scrolls until the plate is visible.
     */
    private void scrollToPlate(String plate) {

        // Try scrolling DOWN first
        for (int i = 0; i < 5; i++) {
            if (isPlatePresent(plate)) return;
            scrollListDown();
        }

        // Then try scrolling UP
        for (int i = 0; i < 5; i++) {
            if (isPlatePresent(plate)) return;
            scrollListUp();
        }

        System.out.println("[DEBUG] Plate NOT found after scrolling: " + plate);
    }

    //  EXPAND & DETAILS

    public void expandSession(String plate) {
        WebElement row = getRowByPlate(plate);
        WebElement toggle = row.findElement(toggleButton);
        toggle.click();
    }

    public SessionDetails getSessionDetails(String plate) {
        expandSession(plate);
        WebElement row = getRowByPlate(plate);

        String endTime = row.findElement(endTimeLabel).getText().trim();
        String zone = row.findElement(zoneLabel).getText().trim();
        String cost = row.findElement(costLabel).getText().trim();

        return new SessionDetails(plate, endTime, zone, cost);
    }

    //  END SESSION (FIXED VERSION)

    @Step("Ending session for plate: {0}")
    public void endSessionByPlate(String plate) {
        scrollToPlate(plate);
        WebElement row = getRowByPlate(plate);
        System.out.println("Trying direct click on deleteButton...");

    // Try popup handling (if direct click worked)
        try {
            WebElement deleteBtn = row.findElement(deleteButton);
            System.out.println("[DEBUG] deleteButton element FOUND. Attempting direct click...");
            deleteBtn.click();
            System.out.println("[DEBUG] Direct click executed.");
        } catch (Exception e) {
            System.out.println("[DEBUG] Direct click failed: " + e.getMessage());
        }
    // Try popup handling (if direct click worked)
        try {
            ActiveAndEndSessionPage popup = new ActiveAndEndSessionPage();
            popup.waitForEndSessionPopup();
            popup.confirmEndSession();
            popup.confirmEndSessionOkIfPresent();
            System.out.println("[DEBUG] Popup handled after direct click.");
            wait(1500);
            return;
        } catch (Exception e) {
            System.out.println("[DEBUG] No popup after direct click. Trying coordinate tap...");
        }
    // Fallback: coordinate tap
        tapDeleteButtonExact();

    // Handle popup after coordinate tap
        ActiveAndEndSessionPage popup = new ActiveAndEndSessionPage();
        popup.waitForEndSessionPopup();
        popup.confirmEndSession();
        popup.confirmEndSessionOkIfPresent();

        wait(1500);
    }

    @Step("Ending Multiple sessions for plates: {0}")
    public void endMultipleSessions(List<String> plates) {
        for (String plate : plates) {
            if (isPlatePresent(plate)) {
                endSessionByPlate(plate);
            }
        }
    }

    @Step("Ending all sessions except: {0}")
    public void endAllExcept(String plateToKeep) {
        List<String> current = new ArrayList<>(getAllPlates());
        for (String plate : current) {
            if (!plate.equals(plateToKeep)) {
                endSessionByPlate(plate);
            }
        }
    }

    @Step("Ending all sessions")
    public void endAllSessions() {
        System.out.println("[DEBUG] Waiting for sessions to load...");
        waitForAtLeastOneSession();

        int count = getSessionCount();
        System.out.println("[DEBUG] REAL session count at start = " + count);

        if (count == 0) {
            System.out.println("[DEBUG] No active friend sessions found.");
            return;
        }

        while (getSessionCount() > 0) {
            String plate = getAllPlates().get(0);
            System.out.println("[DEBUG] Ending session for: " + plate);
            endSessionByPlate(plate);
        }

        System.out.println("[DEBUG] All sessions ended.");
    }

}
