package com.fnb.web.pages.admin;

import com.fnb.utils.helpers.JsonReader;
import com.fnb.web.pages.admin.login.LoginPage;
import com.fnb.web.setup.BaseSetup;


public class HomePage extends BaseSetup {

    public LoginPage navigateToLoginPage() {
        String loginUrl = JsonReader.configObject().getUrlLogin();
        driver.get(loginUrl);
        return new LoginPage();
    }
}
