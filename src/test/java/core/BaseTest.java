package core;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;
import utils.ScreenshotUtils;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        DriverManager.initDriver();
        Reporter.getCurrentTestResult().getTestContext().setAttribute("driver", DriverManager.getDriver());

        // Skip onboarding
        OnboardingPage onboarding = new OnboardingPage();
        onboarding.handleOnboarding();

        // Landing
        LandingPage landing = new LandingPage();
        landing.waitForLandingScreen();
        landing.clickLogin();

        // Login
        LoginPage login = new LoginPage();
        login.login("ppepark1@gmail.com", "MyparkEpark13!");

        // Select PP module
        ModuleSelectorPage selection = new ModuleSelectorPage();
        selection.clickParkPlus();

        // Handle location permission popup
        PermissionHandlerPage permission = new PermissionHandlerPage();
        permission.allowLocationIfPresent();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtils.attachScreenshot();  // Allure screenshot
            ScreenshotUtils.takeScreenshot(result.getName()); // optional local file screenshot
        }

        try {
            ToolbarPage toolbar = new ToolbarPage();
            if (!toolbar.isRightMenuVisible()) {
                throw new RuntimeException("Right menu is NOT visible — cannot logout!");
            }

            toolbar.openRightMenu();

            LogoutPage logout = new LogoutPage();
            logout.logout();

        } catch (Exception e) {
            throw new RuntimeException("Logout failed: " + e.getMessage(), e);
        }
        DriverManager.quitDriver();
    }
}
