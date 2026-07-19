package pages;

import core.BasePage;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ParkAFriendPage extends BasePage {

    private final By startFriendSessionButton =
            By.id("com.cpa.accountManagement:id/btn_show_start_friend_session");

    private final By zoneInput =
            By.id("com.cpa.accountManagement:id/cet_start_friend_session_zone_number");

    private final By licensePlateInput =
            By.id("com.cpa.accountManagement:id/actv_start_friend_session_licence_plate");

    private final By durationDropdown =
            By.id("com.cpa.accountManagement:id/spinner_start_friend_session_zone_duration");

    private final By startButton =
            By.id("com.cpa.accountManagement:id/btn_start_friend_session_start");

    private final By confirmYesButton =
            By.id("android:id/button1");

    private final By activeSessionsButton =
            By.id("com.cpa.accountManagement:id/btn_show_active_friend_sessions");

    // ---------------- BASIC ACTIONS ----------------

    public void clickStartFriendSession() {
        click(startFriendSessionButton);
    }

    public void enterZone(String zone) {
        type(zoneInput, zone);
    }

    public void enterLicensePlate(String plate) {
        type(licensePlateInput, plate);
    }

    public void selectDuration() {
        click(durationDropdown);
        click(By.xpath("(//android.widget.CheckedTextView)[1]"));
    }

    public void clickStart() {
        click(startButton);
    }

    public void confirmStartIfPresent() {
        if (isDisplayed(confirmYesButton)) {
            click(confirmYesButton);
        }
    }

    public void openActiveSessions() {
        click(activeSessionsButton);
    }

    // ---------------- RANDOM PLATE ----------------

    public String generateRandomPlate() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder plate = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            int index = (int) (Math.random() * chars.length());
            plate.append(chars.charAt(index));
        }
        return plate.toString();
    }

    // ---------------- RANDOM ZONE PICKER ----------------

    public String getRandomAllowedZone(List<String> allowedZones) {
        return allowedZones.get((int) (Math.random() * allowedZones.size()));
    }

    // ---------------- HIGH-LEVEL FLOWS ----------------

    //  Start ONE session
    public String startSingleFriendSession(String zone) {
        String plate = generateRandomPlate();

        clickStartFriendSession();
        enterZone(zone);
        enterLicensePlate(plate);
        selectDuration();
        clickStart();
        confirmStartIfPresent();

        return plate;
    }

    //  Start MULTIPLE sessions with SAME zone
    public List<String> startMultipleFriendSessions(String zone, int count) {
        List<String> plates = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String plate = generateRandomPlate();

            clickStartFriendSession();
            enterZone(zone);
            enterLicensePlate(plate);
            selectDuration();
            clickStart();
            confirmStartIfPresent();

            plates.add(plate);
        }

        return plates;
    }

    //  Start MULTIPLE sessions with RANDOM allowed zones
    public List<String> startMultipleFriendSessionsRandomZones(List<String> allowedZones, int count) {
        List<String> plates = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            String zone = getRandomAllowedZone(allowedZones); //  pick from allowed list
            String plate = generateRandomPlate();

            clickStartFriendSession();
            enterZone(zone);
            enterLicensePlate(plate);
            selectDuration();
            clickStart();
            confirmStartIfPresent();

            plates.add(plate);
        }

        return plates;
    }

    // Start sessions using each zone exactly once
    public List<String> startSessionsEachZoneOnce(List<String> zones) {
        List<String> plates = new ArrayList<>();

        for (String zone : zones) {
            String plate = generateRandomPlate();

            clickStartFriendSession();
            enterZone(zone);
            enterLicensePlate(plate);
            selectDuration();
            clickStart();
            confirmStartIfPresent();

            plates.add(plate);
        }

        return plates;
    }

    // Start sessions using each zone exactly once in the order given
    /*    Example:
        Zones = [1604, 2442, 1234]
        Count = 5
        Order = 1604 → 2442 → 1234 → 1604 → 2442 */

    public List<String> startSessionsZonesInOrder(List<String> zones, int count) {
        List<String> plates = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            String zone = zones.get(i % zones.size()); // cycles through list
            String plate = generateRandomPlate();

            clickStartFriendSession();
            enterZone(zone);
            enterLicensePlate(plate);
            selectDuration();
            clickStart();
            confirmStartIfPresent();

            plates.add(plate);
        }

        return plates;
    }

}
