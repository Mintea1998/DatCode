package com.fnb.web.admin.tests.login;

import com.fnb.utils.helpers.Log;

import com.fnb.web.admin.pages.login.LoginPage;
import com.fnb.web.setup.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginScenarioTest extends BaseTest {
    LoginPage loginPage;

    @BeforeClass
    public void navigateToPage() {
        loginPage = adminPages.navigateToLoginPage();
    }

    @Test(priority = 1)
    public void verifyTitleExist() {
//        saveToReport(method.getName(), "report3");
        Log.info("Testcase : check title");
        String expTitle = "Login1";
        assertEquals(loginPage.verifyHeaderDisplay(), expTitle, "Header is not match!");
    }
}


