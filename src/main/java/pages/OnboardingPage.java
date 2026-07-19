package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

/*This Page handles onboarding where after installing and launching the app it will go over the start of the page to click on SKIP button*/

public class OnboardingPage extends BasePage {

    private final By skipButton = By.xpath("//*[@text='SKIP']");
    private final By rightArrow = By.id("com.cpa.accountManagement:id/onboarding_content_btn_right");
    private final By getStartedButton = By.id("com.cpa.accountManagement:id/button_start");

    @Step("Handling onboarding screen to click on SKIP button")
   public void handleOnboarding() {
        log("Launching the MyParking App");
        log("Handling onboarding screen to click SKIP button");

        // 1. Try SKIP first (works on physical device)
        if (isDisplayed(skipButton, 3)) {
            try {
                log("SKIP visible — trying normal click");
                click(skipButton);
                return;
            } catch (Exception e) {
                log("SKIP visible but NOT clickable — switching to arrow navigation");
            }
        }

        // 2. Use right arrow to navigate through onboarding pages (works on Kobiton)
        for (int i = 0; i < 6; i++) {
            if (isDisplayed(getStartedButton, 2)) {
                log("GET STARTED button found — clicking it");
                click(getStartedButton);
                return;
            }

            if (isDisplayed(rightArrow, 2)) {
                log("Clicking right arrow to move to next onboarding page");
                click(rightArrow);
                pause(500);
            }
        }

        // 3. Final check for GET STARTED
        if (isDisplayed(getStartedButton, 3)) {
            log("Clicking GET STARTED");
            click(getStartedButton);
        } else {
            log("GET STARTED not found — onboarding may already be skipped");
        }
    }
}
