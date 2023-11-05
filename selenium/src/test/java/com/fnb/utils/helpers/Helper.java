package com.fnb.utils.helpers;

import com.fnb.web.setup.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class Helper extends BaseSetup {

    public Helper(WebDriver driver) {
        this.driver = driver;
    }

    public void checkImgBroken(int iBrokenImageCount, String status) {
        try {
            iBrokenImageCount = 0;
            List<WebElement> image_list = driver.findElements(By.tagName("img"));
            /* Print the total number of images on the page */
            Log.info("The page under test has " + image_list.size() + " images");
            for (WebElement img : image_list) {
                if (img != null) {
                    if (img.getAttribute("naturalWidth").equals("0")) {
                        Log.info(img.getAttribute("outerHTML") + " is broken.");
                        iBrokenImageCount++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "failed";
            System.out.println(e.getMessage());
        }
        status = "passed";
        Log.info(iBrokenImageCount + " broken images");
    }

    public void isElementNotPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
        }
    }

    public void getDataTable1(WebDriver driver) {
        WebElement table = driver.findElement(By.xpath("//table[@id='table1']"));
        //define row of table
        List<WebElement> rows_table = table.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();
        //System.out.println("rows: " + (rows_count - 1));
        for (int row = 1; row < rows_count; row++) {
            //To locate columns(cells) of that specific row.
            List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
            //To calculate no of columns (cells). In that specific row.
            int columns_count = Columns_row.size();
            System.out.println("Number of cells In Row " + row + " Table 1 are " + columns_count);
            //Loop will execute till the last cell of that specific row.
            for (int column = 1; column < columns_count; column++) {
                // To retrieve text from that specific cell.
                String celtext = Columns_row.get(column).getText();
                System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
            }
            System.out.println("-------------------------------------------------- ");
        }
    }

}
