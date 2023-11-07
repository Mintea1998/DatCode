package com.fnb.web.pos.pages.register;

import com.fnb.web.setup.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPosPage extends Setup {
    @FindBy(xpath = "//*[@id=\"basic\"]/div/h1")
    private WebElement title1;

    public RegisterPosPage() {
        PageFactory.initElements(driver, this);
    }

    public String verifyHeaderDisplay() {
        return title1.getText();
    }

}
