package com.fnb.web.admin.pages.register;

import com.fnb.web.setup.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends Setup {
    @FindBy(xpath = "//h1[contains(text(),'Store information')]")
    private WebElement title1;

    public RegisterPage() {
        PageFactory.initElements(driver, this);
    }

    public String verifyHeaderDisplay() {
        return title1.getText();
    }

}
