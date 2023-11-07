package com.fnb.web.admin.tests.register;

import com.fnb.utils.helpers.Log;
import com.fnb.web.admin.pages.login.LoginPage;
import com.fnb.web.admin.pages.register.RegisterPage;
import com.fnb.web.setup.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegisterScenarioTest extends BaseTest {
    RegisterPage registerPage;

    @BeforeClass
    public void navigateToPage() {
        registerPage = adminPages.navigateToRegisterPage();
    }

    @Test(priority = 1)
    public void verifyTitleExist() {
//        saveToReport(method.getName(), "report3");
        Log.info("Testcase : check title register fail");
        String expTitle = "Store information";
        assertEquals(registerPage.verifyHeaderDisplay(), expTitle, "Header is not match!");
    }
}

