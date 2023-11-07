package com.fnb.web.setup;

import com.fnb.utils.helpers.JsonReader;
import com.fnb.drivermanager.DriverFactory;
import com.fnb.web.admin.pages.PagesAdminSetup;
import com.fnb.web.pos.pages.PagesPosSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;

public class Setup {
    public static com.fnb.utils.helpers.Helper helper;
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    public static SoftAssert softAssert;
    public static Robot robot;
    public static JsonReader.ConfigObject configObject;
    public static String baseUrl = null;
//    public static String platform;

    public static WebDriver getDriver() {
        return driver;
    }

    public void setupDriver(String platform) throws AWTException {
        configObject = JsonReader.configObject(platform);
        String browserType = JsonReader.configObject(platform).getBrowser();
        driver = DriverFactory.initDriver(browserType);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
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

    public PagesAdminSetup navigateToAdminPage() throws IOException {
        baseUrl = configObject.getUrlBase();
        //https://stag-admin.beecowdeal.vn/
        System.out.println("navigateToHomePage"+baseUrl);
        driver.get(baseUrl);
        return new PagesAdminSetup();
    }
    public PagesPosSetup navigateToPOSPage() throws IOException {
        baseUrl = configObject.getUrlBase();
        //https://stag-admin.beecowdeal.vn/
        System.out.println("navigateToHomePage"+baseUrl);
        driver.get(baseUrl);
        return new PagesPosSetup();
    }


    public void gobacktoHomePage() {
        driver.navigate().to(baseUrl);
    }

    public void tearDownDriver() {
        driver.quit();
    }

}
