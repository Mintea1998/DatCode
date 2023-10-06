package com.fnb.pages.login;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fnb.utils.FnbLibrary;
import com.fnb.utils.FnbLibrary.ConfigObject;

public class LoginPage {
    private FnbLibrary fnbLibrary;

    @FindBy(id = "basic_userName")
    private WebElement txtUserName;

    @FindBy(id = "basic_password")
    private WebElement txtPassword;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnLogin;

    @FindBy(xpath = "//*[@id=\"basic_userName_help\"]/div")
    private WebElement lblErrorUserName;

    @FindBy(xpath = "//*[@id=\"basic_password_help\"]/div")
    private WebElement lblErrorPassword;

    @FindBy(xpath = "//*[@id=\"basic\"]/div/div[2]/p")
    private WebElement lblErrorWrongUserNamePassword;

    public LoginPage(FnbLibrary library) {
        fnbLibrary = library;
        ConfigObject config = FnbLibrary.configObject();
        String urlLogin = config.getUrlLogin();
        fnbLibrary.openUrl(urlLogin);
        fnbLibrary.waitUrl(urlLogin);
        PageFactory.initElements(fnbLibrary.getDriver(), this);
    }

    public void enterUsername(String username) {
        txtUserName.sendKeys(username);
    }

    public void enterPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void clickLoginButton() {
        btnLogin.click();
    }

    public String getErrorMessageUserName() {
        fnbLibrary.explicitWait(lblErrorUserName);
        return lblErrorUserName.getText();
    }

    public String getErrorMessagePassword() {
        fnbLibrary.explicitWait(lblErrorPassword);
        return lblErrorPassword.getText();
    }

    public String getErrorMessageWrongUserNamePassword() {
        fnbLibrary.explicitWait(lblErrorWrongUserNamePassword);
        return lblErrorWrongUserNamePassword.getText();
    }

    public void clearUsername() {
        txtUserName.sendKeys(Keys.CONTROL + "a");
        txtUserName.sendKeys(Keys.DELETE);
    }

    public void clearPassword() {
        txtPassword.sendKeys(Keys.CONTROL + "a");
        txtPassword.sendKeys(Keys.DELETE);
    }

    public void clearAll() {
        clearUsername();
        clearPassword();
    }
}