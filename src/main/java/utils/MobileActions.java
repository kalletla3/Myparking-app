package utils;

import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class MobileActions {

    private static AppiumDriver getDriver() {
        return (AppiumDriver) DriverManager.getDriver();
    }

    // -----------------------------
    // GENERIC SWIPE USING W3C ACTIONS
    // -----------------------------
    private static void swipe(int startX, int startY, int endX, int endY, int durationMs) {
        AppiumDriver driver = getDriver();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(durationMs),
                PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    // -----------------------------
    // SWIPE UP
    // -----------------------------
    public static void swipeUp() {
        AppiumDriver driver = getDriver();
        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.80);
        int endY = (int) (size.height * 0.20);

        swipe(startX, startY, startX, endY, 600);
    }

    // -----------------------------
    // SWIPE DOWN
    // -----------------------------
    public static void swipeDown() {
        AppiumDriver driver = getDriver();
        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.20);
        int endY = (int) (size.height * 0.80);

        swipe(startX, startY, startX, endY, 600);
    }

    // -----------------------------
    // SWIPE LEFT
    // -----------------------------
    public static void swipeLeft() {
        AppiumDriver driver = getDriver();
        Dimension size = driver.manage().window().getSize();

        int startY = size.height / 2;
        int startX = (int) (size.width * 0.80);
        int endX = (int) (size.width * 0.20);

        swipe(startX, startY, endX, startY, 600);
    }

    // -----------------------------
    // SWIPE RIGHT
    // -----------------------------
    public static void swipeRight() {
        AppiumDriver driver = getDriver();
        Dimension size = driver.manage().window().getSize();

        int startY = size.height / 2;
        int startX = (int) (size.width * 0.20);
        int endX = (int) (size.width * 0.80);

        swipe(startX, startY, endX, startY, 600);
    }

    // -----------------------------
    // SCROLL UNTIL ELEMENT VISIBLE
    // -----------------------------
    public static void scrollUntilVisible(By locator, int maxScrolls) {
        AppiumDriver driver = getDriver();

        for (int i = 0; i < maxScrolls; i++) {
            try {
                if (driver.findElement(locator).isDisplayed()) {
                    return;
                }
            } catch (Exception ignored) {}

            swipeUp();
        }

        throw new RuntimeException("Element not found after scrolling: " + locator);
    }

    // -----------------------------
    // TAP BY COORDINATES
    // -----------------------------
    public static void tap(int x, int y) {
        AppiumDriver driver = getDriver();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }
}
