package com.fnb.web.pos.scenario_test.login;

import com.fnb.utils.helpers.Log;
import com.fnb.web.pos.pages.login.LoginPosPage;
import com.fnb.web.setup.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginPosScenarioTest extends BaseTest {
    LoginPosPage loginPosPage;

    @BeforeClass
    public void navigateToPage() {
        loginPosPage = posPage.navigateToLoginPage();
    }

    @Test(priority = 1)
    public void verifyTitleExist() {
//        saveToReport(method.getName(), "report3");
        Log.info("Testcase : check title");
        String expTitle = "Login";
        assertEquals(loginPosPage.verifyHeaderDisplay(), expTitle, "Header is not match!");
    }
}


