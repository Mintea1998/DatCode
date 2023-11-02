package com.fnb.utils.helpers;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.fnb.web.admin.setup.BaseSetup.driver;

public class CaptureHelpers {

    //Lấy đường dẫn đến project hiện tại
    static String projectPath = System.getProperty("user.dir") + "/";
    //Tạo format ngày giờ để xíu gắn dô cái name của screenshot hoặc record video
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static void captureScreenshot(String screenName) throws IOException {
        PropertiesReader envProFile = new PropertiesReader("dev");
        Reporter.log("Driver for Screenshot: " + driver);
        // Tạo tham chiếu đối tượng của TakesScreenshot với dirver hiện tại
        TakesScreenshot ts = (TakesScreenshot) driver;
        // Gọi hàm getScreenshotAs để chuyển hóa hình ảnh về dạng FILE
        File source = ts.getScreenshotAs(OutputType.FILE);
        //Kiểm tra folder nếu không tồn tại thì tạo folder
        File theDir = new File(projectPath + PropertiesReader.getPropValue("exportCapturePath"));
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        // Chổ này đặt tên thì truyền biến "screenName" gán cho tên File chụp màn hình
        FileHandler.copy(source, new File(projectPath + PropertiesReader.getPropValue("exportCapturePath") + "/" + screenName + "_" + dateFormat.format(new Date()) + ".png"));
        System.out.println("Screenshot taken: " + screenName);
        Reporter.log("Screenshot taken current URL: " + driver.getCurrentUrl(), true);
    }
}


