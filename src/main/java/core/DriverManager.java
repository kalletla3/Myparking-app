package core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;

public class DriverManager {

    private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        try {
// ============================================================
//  OPTION 1: PHYSICAL DEVICE (Uncomment this block to run local)
// ============================================================
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setAutomationName("UiAutomator2");

            options.setDeviceName(ConfigReader.get("deviceName"));
            options.setUdid(ConfigReader.get("udid"));
            options.setAppPackage(ConfigReader.get("appPackage"));
            options.setAppActivity(ConfigReader.get("appActivity"));


            options.setNoReset(false);
            options.setFullReset(false);

            // Enable video recording support
            options.setCapability("recordVideo", true);

            AndroidDriver androidDriver =
                    new AndroidDriver(new URL(ConfigReader.get("appiumURL")), options);

            driver.set(androidDriver);


//============================================================
//  OPTION 2: KOBITON (Uncomment this block to run on Kobiton)
// ============================================================
//            String username = ConfigReader.get("kobitonUsername");
//            String apiKey = ConfigReader.get("kobitonApiKey");
//
//            String kobitonServerUrl =
//                    "https://" + username + ":" + apiKey + "@api.kobiton.com/wd/hub";
//
//            UiAutomator2Options options = new UiAutomator2Options();
//            options.setPlatformName("Android");
//            options.setAutomationName("UiAutomator2");
//
//            options.setDeviceName(ConfigReader.get("kobitonDeviceName"));
//            options.setPlatformVersion(ConfigReader.get("kobitonPlatformVersion"));
//
//            options.setAppPackage(ConfigReader.get("kobitonAppPackage"));
//            options.setAppActivity(ConfigReader.get("kobitonAppActivity"));
//
//            options.setCapability("sessionName", "Automation Test");
//            options.setCapability("deviceOrientation", "portrait");
//            options.setCapability("captureScreenshots", true);
//            options.setCapability("recordVideo", true);
//
//            options.setNoReset(false);
//            options.setFullReset(true);
//
//            AndroidDriver androidDriver =
//                    new AndroidDriver(new URL(kobitonServerUrl), options);
//
//            driver.set(androidDriver);
//            return;

        } catch (Exception e) {
            throw new RuntimeException("Driver initialization failed: " + e.getMessage());
        }
        }

        public static AndroidDriver getDriver () {
            return driver.get();
        }

        public static void quitDriver () {
            if (driver.get() != null) {
                driver.get().quit();
                driver.remove();
            }
        }
    }