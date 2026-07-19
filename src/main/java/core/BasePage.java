package core;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final int TIMEOUT = 20;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    // WAIT UTILITIES

    protected void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForVisibility(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(timeoutSeconds)
        );
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForClickability(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ELEMENT ACTIONS

    protected void click(By locator) {
        waitForClickability(locator);
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // NEW: FIND ELEMENTS

    protected WebElement find(By locator) {
        waitForVisibility(locator);
        return driver.findElement(locator);
    }

    public WebElement findElement(By locator) {
        waitForVisibility(locator);
        return driver.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

      // NEW: NON-WAIT isDisplayed

    public boolean isDisplayed(By locator) {
        try {
            return DriverManager.getDriver().findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDisplayed(By locator, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // NEW: SIMPLE WAIT (sleep)

    protected void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    //Pause

    public void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }

    // LOGGING

    protected void log(String message) {
        System.out.println("[LOG] " + message);
    }


    // Co-ordinates

    public void tapByCoordinates(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getDriver().perform(Collections.singletonList(tap));
    }

    // NEW: SCROLL DOWN SUPPORT
    public void scrollDown() {
        try {
            driver.findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
                    )
            );
        } catch (Exception e) {
            System.out.println("[LOG] No more scrollable area");
        }
    }

    public void clearAndType(By locator, String text) {
        try {
            var el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            el.clear();
            el.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Unable to clear and type into element: " + locator, e);
        }
    }

    public boolean isEnabled(By locator) {
        try {
            var el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return el.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}
