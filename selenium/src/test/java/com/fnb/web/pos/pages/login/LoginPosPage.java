package com.fnb.web.pos.pages.login;

import com.fnb.web.setup.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPosPage extends Setup {
    @FindBy(xpath = "//*[@id=\"basic\"]/div/h1")
    private WebElement title;

    public LoginPosPage() {
        PageFactory.initElements(driver, this);
    }

    public String verifyHeaderDisplay() {
        return title.getText();
    }
}
