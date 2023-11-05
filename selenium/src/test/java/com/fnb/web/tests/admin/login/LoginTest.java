package com.fnb.web.tests.admin.login;

import com.fnb.utils.helpers.Log;
import com.fnb.web.pages.admin.login.LoginPage;
import com.fnb.web.setup.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeClass
    public void navigateToPage() {
        loginPage = homePage.navigateToLoginPage();
    }

    @Test(priority = 1)
    public void verifyTitleExist() {
//        saveToReport(method.getName(), "report3");
        Log.info("Testcase : check title");
        String expTitle = "Login";
        assertEquals(loginPage.verifyHeaderDisplay(), expTitle, "Header is not match!");
    }
}
