package com.fnb.web.scenario_tests;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fnb.web.pages.login.LoginPage;
import com.fnb.web.pages.login.DataTest;
import com.fnb.utils.Config;
import com.fnb.utils.Config.ConfigObject;
import com.fnb.utils.FnbLibrary;

public class Login {
    private FnbLibrary fnbLibrary;
    private LoginPage loginPage;
    private String errorMessage = "";
    private String URL_HOMEPAGE = "";
    private Integer TIME_OUT = 0;

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        ConfigObject config = Config.configObject();
        URL_HOMEPAGE = config.getUrlHome();
        TIME_OUT = config.getTimeOut();
        // Setup WebDriver
        fnbLibrary = new FnbLibrary(config.getBrowser());
        fnbLibrary.getDriver();
        fnbLibrary.webDriverWait(TIME_OUT);

        // Setup login page
        loginPage = new LoginPage(fnbLibrary);
        fnbLibrary.takesScreenshot(DataTest.SCREENSHOT_PATH + "0_setup_login");
    }

    @AfterTest
    public void afterTest() {
        fnbLibrary.closeBrowser();
    }

    @Test
    public void testCase1() throws IOException, InterruptedException {
        // CASE 1: input userName, not input password -> click btn login
        loginPage.enterUsername(DataTest.INPUT_USERNAME);
        fnbLibrary.takesScreenshot(DataTest.SCREENSHOT_PATH + "1");
        loginPage.clickLoginButton();
        errorMessage = loginPage.getErrorMessagePassword();
        assert errorMessage.equals(DataTest.ERROR_ENTER_PASSWORD);
        fnbLibrary.takesScreenshot(DataTest.SCREENSHOT_PATH + "1");
    }

    @Test
    public void testCase2() throws IOException, InterruptedException {
        // CASE 2: not input userName, input password
        loginPage.clearUsername();
        fnbLibrary.takesScreenshot(DataTest.SCREENSHOT_PATH + "2");
        loginPage.enterPassword(DataTest.INPUT_PASSWORD);
        errorMessage = loginPage.getErrorMessageUserName();
        assert errorMessage.equals(DataTest.ERROR_ENTER_USERNAME);
        fnbLibrary.takesScreenshot(DataTest.SCREENSHOT_PATH + "2");
    }

    @Test
    public void testCase3() throws IOException, InterruptedException {
        // CASE 3: input invalid userName, input password
        loginPage.enterUsername(DataTest.INPUT_INVALID_USERNAME);
        fnbLibrary.takesScreenshot(DataTest.SCREENSHOT_PATH + "3");
        errorMessage = loginPage.getErrorMessageUserName();
        assert errorMessage.equals(DataTest.ERROR_INVALID_USERNAME);
        fnbLibrary.takesScreenshot(DataTest.SCREENSHOT_PATH + "3");
    }

    @Test
    public void testCase4() throws IOException, InterruptedException {
        // CASE 4: input wrong userName or password -> click btn login
        loginPage.enterUsername(DataTest.INPUT_USERNAME_NOT_EXIST);
        loginPage.enterPassword(DataTest.INPUT_PASSWORD);
        fnbLibrary.takesScreenshot(DataTest.SCREENSHOT_PATH + "4");
        loginPage.clickLoginButton();
        errorMessage = loginPage.getErrorMessageWrongUserNamePassword();
        System.out.println("errorMessage => " + errorMessage);
        assert errorMessage.equals(DataTest.ERROR_WRONG_USERNAME_PASSWORD);
        fnbLibrary.takesScreenshot(DataTest.SCREENSHOT_PATH + "4");
    }

    @Test
    public void testCase5() throws IOException, InterruptedException {
        // CASE 5: input userName,password -> click btn login
        loginPage.clearAll();
        loginPage.enterUsername(DataTest.INPUT_USERNAME);
        loginPage.enterPassword(DataTest.INPUT_PASSWORD);
        fnbLibrary.takesScreenshot(DataTest.SCREENSHOT_PATH + "5");
        loginPage.clickLoginButton();
        fnbLibrary.waitUrl(URL_HOMEPAGE);
        String nextPageUrl = fnbLibrary.getCurrentUrl();
        assert nextPageUrl.equals(URL_HOMEPAGE);
        fnbLibrary.takesScreenshot(DataTest.SCREENSHOT_PATH + "5");
    }
}
