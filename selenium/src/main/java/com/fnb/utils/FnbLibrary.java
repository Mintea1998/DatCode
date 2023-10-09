package com.fnb.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fnb.utils.Config.ConfigObject;

public class FnbLibrary {
    private static WebDriver driver;
    private WebDriverWait wait;
    ConfigObject config = Config.configObject();

    public static String getOsName() {
        String osName = System.getProperty("os.name").toLowerCase();
        // Windown: os = win, Linux: os = nix || nux, macOS: os = mac
        return osName;
    }

    public FnbLibrary(String browser) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if (browser.equalsIgnoreCase("chrome")) {
            System.out.println("-----pathWebdriver-----: " + config.getPathWebDriverChrome());
            System.setProperty("webdriver.chrome.driver", config.getPathWebDriverChrome());
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            // System.setProperty("webdriver.gecko.driver",
            // "src/main/java/com/webdriver/firefox/geckodriver.exe");
            // driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser name");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait webDriverWait(long timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait;
    }

    public void explicitWait(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitText(By locator, String value) {
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, value));
    }

    public void waitUrl(String value) {
        wait.until(ExpectedConditions.urlToBe(value));
    }

    public void openUrl(String url) {
        driver.manage().window().maximize();
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public String getText(By locator) {
        WebElement el = driver.findElement(locator);
        return el.getText();
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void takesScreenshot(String pathTestCase) throws IOException, InterruptedException {
        String currentDate = getCurrentDate();
        String folderPath = config.getPathScreenshot() + "/" + currentDate;
        if (pathTestCase != "" || pathTestCase != null) {
            folderPath = folderPath + "/" + pathTestCase;
        }
        File folder = new File(folderPath);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdir();
        }
        Thread.sleep(500);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File[] files = folder.listFiles();
        Integer countFile = 0;
        if (files != null) {
            List<File> fileList = Arrays.asList(files);
            for (File file : fileList) {
                if (file.isFile()) {
                    countFile++;
                }
            }
        }
        FileUtils.copyFile(screenshotFile, new File(folderPath + "/" + countFile + ".png"));
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void deleteFilesInSubfolders(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFilesInSubfolders(file);
                } else if (file.isFile()) {
                    file.delete();
                    System.out.println("-----Deleted-----: " + file.getAbsolutePath());
                }
            }
        }
    }

    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }
}
