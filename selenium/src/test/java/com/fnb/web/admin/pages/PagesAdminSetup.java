package com.fnb.web.admin.pages;

import com.fnb.utils.helpers.JsonReader;
import com.fnb.web.admin.pages.login.LoginPage;
import com.fnb.web.admin.pages.register.RegisterPage;
import com.fnb.web.setup.Setup;
import com.google.gson.JsonObject;


public class PagesAdminSetup extends Setup {
    public LoginPage navigateToLoginPage() {
        String loginUrl = configObject.getUrlLogin();
        System.out.println(loginUrl);
        driver.get(loginUrl);
        return new LoginPage();
    }

    public RegisterPage navigateToRegisterPage() {
        String registerUrl = configObject.getRegister();
        System.out.println(registerUrl);
        driver.get(registerUrl);
        return new RegisterPage();
    }
}
