package com.fnb.web.setup;

import com.fnb.web.admin.pages.PagesAdminSetup;
import com.fnb.web.pos.pages.PagesPosSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    public static Setup setup;
    public static Method method;
    public static PagesAdminSetup adminPages;
    public static PagesPosSetup posPage;
//    public static PagesAdminSetup homePage;

    public WebDriver getDriver() {
        return Setup.driver;
    }

    @BeforeSuite
    public void beforeSuite() {
        setup = new Setup();
    }

    @Parameters({"platform"})
    @BeforeTest
    public void initTestSetup(String platform) throws AWTException, IOException {
        setup.setupDriver(platform);
        adminPages = setup.navigateToAdminPage();
        posPage = setup.navigateToPOSPage();
    }

    @AfterClass
    public void endSession() {
        setup.gobacktoHomePage();
    }

    @AfterTest
    public void tearDown() throws Exception {
        setup.tearDownDriver();
    }
}
