package com.fnb.test_ng.web;

import java.io.File;
import java.io.IOException;

import org.testng.TestNG;
import org.testng.annotations.Test;

import com.fnb.web.scenario_tests.Login;
import com.fnb.utils.FnbLibrary;
import com.fnb.utils.Config;
import com.fnb.utils.Config.ConfigObject;

public class TestAll {

    @Test
    public void TestNG() throws IOException {
        Setup();
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[] {
                Login.class,
        });
        testNG.run();
    }

    public void Setup() throws IOException {
        String currentDate = FnbLibrary.getCurrentDate();
        ConfigObject config = Config.configObject();
        String folderPath = config.getPathScreenshot() + "/" + currentDate;
        FnbLibrary.deleteFilesInSubfolders(new File(folderPath));
    }
}
