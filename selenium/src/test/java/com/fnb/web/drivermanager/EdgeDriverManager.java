package com.fnb.web.drivermanager;

import com.fnb.web.drivermanager.DriverManagerBrowser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class EdgeDriverManager extends DriverManagerBrowser {
    @Override
    public WebDriver getDriver(){
        System.out.println("Launching edge browser...");
//        Log.info("Launching edge browser...");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
