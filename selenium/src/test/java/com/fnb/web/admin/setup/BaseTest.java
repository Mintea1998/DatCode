package com.fnb.web.admin.setup;

import com.fnb.web.admin.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    public static BaseSetup setup;
    public static Method method;
    public static HomePage homePage;

    public WebDriver getDriver() {
        return BaseSetup.driver;
    }

    @BeforeSuite
    public void beforeSuite() {
        setup = new BaseSetup();
    }

    @BeforeTest
    public void initTestSetup() throws AWTException, IOException {
        setup.setupDriver();
        homePage = setup.navigateToHomePage();
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
