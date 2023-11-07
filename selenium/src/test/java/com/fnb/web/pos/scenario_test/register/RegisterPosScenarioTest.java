package com.fnb.web.pos.scenario_test.register;

import com.fnb.utils.helpers.Log;
import com.fnb.web.pos.pages.register.RegisterPosPage;
import com.fnb.web.setup.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegisterPosScenarioTest extends BaseTest {
    RegisterPosPage registerPosPage;

    @BeforeClass
    public void navigateToPage() {
        registerPosPage = posPage.navigateToRegisterPage();
    }

    @Test(priority = 1)
    public void verifyTitleExist() {
//        saveToReport(method.getName(), "report3");
        Log.info("Testcase : check title register fail");
        String expTitle = "Login1";
        assertEquals(registerPosPage.verifyHeaderDisplay(), expTitle, "Header is not match!");
    }
}

