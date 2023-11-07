package com.fnb.web.admin.pages.login;

import com.fnb.web.setup.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Setup {
    @FindBy(xpath = "//h1[contains(text(),'Login')]")
    private WebElement title;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public String verifyHeaderDisplay() {
        return title.getText();
    }
}
