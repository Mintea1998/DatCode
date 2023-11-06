package com.fnb.web.setup;

import com.fnb.utils.helpers.JsonReader;
import com.fnb.drivermanager.DriverFactory;
import com.fnb.web.admin.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;

public class BaseSetup {
    public static com.fnb.utils.helpers.Helper helper;
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    public static SoftAssert softAssert;
    public static Robot robot;
    public JsonReader.ConfigObject configObject;
    public static String baseUrl = null;

    public static WebDriver getDriver() {
        return driver;
    }

    public void setupDriver() throws AWTException {
        String browserType = JsonReader.configObject().getBrowser();
        driver = DriverFactory.initDriver(browserType);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        configObject = JsonReader.configObject();
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        robot = new Robot();

//        helper = new Helper(driver);
    }

    public static String getOsName() {
        String osName = System.getProperty("os.name").toLowerCase();
        // Windown: os = win, Linux: os = nix || nux, macOS: os = mac
        return osName;
    }

    public HomePage navigateToHomePage() throws IOException {
        baseUrl = configObject.getUrlBase();
        driver.get(baseUrl);
        return new HomePage();
    }

    public void gobacktoHomePage() {
        driver.navigate().to(baseUrl);
    }

    public void tearDownDriver() {
        driver.quit();
    }

}
