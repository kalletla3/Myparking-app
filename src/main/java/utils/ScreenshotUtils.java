package utils;

import core.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    public static String takeScreenshot(String testName) {
        File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM_dd_yyyy_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String path = "screenshots/" + testName + "_" + timestamp + ".png";

        File dest = new File(path);
        try {
            Files.createDirectories(dest.getParentFile().toPath());
            Files.copy(src.toPath(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        return ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }

}
