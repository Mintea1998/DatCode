package com.fnb.web.pos.pages;

import com.fnb.utils.helpers.JsonReader;
import com.fnb.web.admin.pages.login.LoginPage;
import com.fnb.web.admin.pages.register.RegisterPage;
import com.fnb.web.pos.pages.login.LoginPosPage;
import com.fnb.web.pos.pages.register.RegisterPosPage;
import com.fnb.web.setup.Setup;


public class PagesPosSetup extends Setup {

    public LoginPosPage navigateToLoginPage() {
        String loginUrl = configObject.getUrlLogin();
//        String loginUrl = JsonReader.configObject("pos").getUrlLogin();
        System.out.println(loginUrl);
        driver.get(loginUrl);
        return new LoginPosPage();
    }

    public RegisterPosPage navigateToRegisterPage() {
        String registerUrl = configObject.getUrlLogin();
        System.out.println(registerUrl);
        driver.get(registerUrl);
        return new RegisterPosPage();
    }
}
