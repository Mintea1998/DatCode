package com.fnb.utils;

import java.io.File;
import java.io.FileReader;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FnbLibrary {
    private static WebDriver driver;
    private WebDriverWait wait;

    public static String getOsName() {
        String os = System.getProperty("os.name").toLowerCase();
        // Windown: os = win, Linux: os = nix || nux, macOS: os = mac
        return os;
    }

    public FnbLibrary(String browser) {
        String osName = getOsName();
        if (osName == "") {
            throw new IllegalArgumentException("Invalid os");
        }

        String webDriverPath = "";
        if (osName.contains("win")) {
            webDriverPath = "src/main/java/com/fnb/webdriver/windown/chrome/chromedriver.exe";
        } else if (osName.contains("nix") || osName.contains("nux")) {
            webDriverPath = "src/main/java/com/fnb/webdriver/linux/chrome/chromedriver";
        } else if (osName.contains("mac")) {
            webDriverPath = "";
        }

        if (webDriverPath == "") {
            throw new IllegalArgumentException("Invalid webdriver path");
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", webDriverPath);
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
        ConfigObject config = configObject();
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
                    System.out.println("Deleted: " + file.getAbsolutePath());
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

    public static JsonObject readConfigFile(String configFile) {
        try {
            // Read the configuration file
            FileReader reader = new FileReader(configFile);
            JsonObject config = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            return config;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ConfigObject configObject() {
        // Read the configuration file
        String configFile = "config.json";
        JsonObject config = readConfigFile(configFile);
        // Create a Gson instance
        Gson gson = new Gson();

        // Create a JsonObject representing the object
        JsonObject jsonObject = new JsonObject();
        String env = config.get("env").getAsString();
        if (env == "prod") {
            env = config.get("prod").getAsString();
        } else if (env == "stag") {
            env = config.get("stag").getAsString();
        } else {
            env = config.get("dev").getAsString();
        }
        jsonObject.addProperty("env", config.get("env").getAsString());
        jsonObject.addProperty("timeOut", config.get("timeout").getAsNumber());
        jsonObject.addProperty("browser", config.get("browser").getAsString());
        jsonObject.addProperty("urlHome", env + config.get("route").getAsJsonObject().get("home").getAsString());
        jsonObject.addProperty("urlLogin", env + config.get("route").getAsJsonObject().get("login").getAsString());
        jsonObject.addProperty("pathScreenshot", config.get("path").getAsJsonObject().get("screenshot").getAsString());

        // Deserialize the JsonObject to a MyObject instance
        ConfigObject configObject = gson.fromJson(jsonObject, ConfigObject.class);

        // Return the object
        return configObject;
    }

    public class ConfigObject {
        private String env;
        private String browser;
        private Integer timeOut;
        private String urlHome;
        private String urlLogin;
        private String pathScreenshot;

        public String getEnv() {
            return env;
        }

        public void setEnvDev(String env) {
            this.env = env;
        }

        public String getBrowser() {
            return browser;
        }

        public void setBrowser(String browser) {
            this.browser = browser;
        }

        public Integer getTimeOut() {
            return timeOut;
        }

        public void setTimeOut(Integer timeOut) {
            this.timeOut = timeOut;
        }

        public String getUrlHome() {
            return urlHome;
        }

        public void setUrlHome(String urlHome) {
            this.urlHome = urlHome;
        }

        public String getUrlLogin() {
            return urlLogin;
        }

        public void setUrlLogin(String urlLogin) {
            this.urlLogin = urlLogin;
        }

        public String getPathScreenshot() {
            return pathScreenshot;
        }

        public void setPathScreenshot(String pathScreenshot) {
            this.pathScreenshot = pathScreenshot;
        }

    }
}
