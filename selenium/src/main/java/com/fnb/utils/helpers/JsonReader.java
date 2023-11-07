package com.fnb.utils.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    public static JsonObject readConfigFile(String configFile) {
        try {
            // Read the configuration file
            FileReader reader = new FileReader(configFile);
            JsonObject config = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
            return config;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ConfigObject configObject(String platform) {
        // Read the configuration file
        String configFile = "src/test/resources/config.json";
        JsonObject config = readConfigFile(configFile);
        // Create a Gson instance
        Gson gson = new Gson();

        // Create a JsonObject representing the object
        JsonObject jsonObject = new JsonObject();
//        JsonObject webConfig = config.get("admin").getAsJsonObject();
        JsonObject webConfig = config.get(platform).getAsJsonObject();
        String env = webConfig.get("env").getAsString();
        System.out.println("-----env-----: " + env);
        if (env.equals("prod")) {
            env = webConfig.get("prod").getAsString();
        } else if (env.equals("stag")) {
            env = webConfig.get("stag").getAsString();
        } else {
            env = webConfig.get("dev").getAsString();
        }
        jsonObject.addProperty("env", webConfig.get("env").getAsString());
        jsonObject.addProperty("timeOut", webConfig.get("timeout").getAsNumber());
        jsonObject.addProperty("browser", webConfig.get("browser").getAsString());
        jsonObject.addProperty("urlHome",
                env + webConfig.get("route").getAsJsonObject().get("home").getAsString());
        jsonObject.addProperty("urlLogin",
                env + webConfig.get("route").getAsJsonObject().get("login").getAsString());
        jsonObject.addProperty("pathScreenshot",
                webConfig.get("path").getAsJsonObject().get("screenshot").getAsString());
        jsonObject.addProperty("urlBase", env);
        jsonObject.addProperty("register", env + webConfig.get("route").getAsJsonObject().get("registerAccount").getAsString());

        //TODO
        String osName = "win";
        if (osName.contains("win")) {
            System.out.println("-----osName-----: " + osName);
            jsonObject.addProperty("pathWebDriverChrome",
                    webConfig.get("web_driver_path").getAsJsonObject().get("win").getAsJsonObject().get("chrome")
                            .getAsString());
        } else if (osName.contains("nix") || osName.contains("nux")) {
            System.out.println("-----osName-----: " + osName);
            jsonObject.addProperty("pathWebDriverChrome",
                    webConfig.get("web_driver_path").getAsJsonObject().get("linux").getAsJsonObject().get("chrome")
                            .getAsString());
        } else {
            // nothing
        }

        ConfigObject configObject = gson.fromJson(jsonObject, ConfigObject.class);
        // Return the object
        return configObject;
    }

    public class ConfigObject {
        private String env;
        private String browser;
        private Integer timeOut;
        private String urlHome;
        private String urlLogin;
        private String pathScreenshot;
        private String pathWebDriverChrome;

        public String getRegister() {
            return register;
        }

        public void setRegister(String register) {
            this.register = register;
        }

        private String register;

        public String getUrlBase() {
            return urlBase;
        }

        public void setUrlBase(String urlBase) {
            this.urlBase = urlBase;
        }

        private String urlBase;

        public String getEnv() {
            return env;
        }

        public void setEnvDev(String env) {
            this.env = env;
        }

        public String getBrowser() {
            return browser;
        }

        public void setBrowser(String browser) {
            this.browser = browser;
        }

        public Integer getTimeOut() {
            return timeOut;
        }

        public void setTimeOut(Integer timeOut) {
            this.timeOut = timeOut;
        }

        public String getUrlHome() {
            return urlHome;
        }

        public void setUrlHome(String urlHome) {
            this.urlHome = urlHome;
        }

        public String getUrlLogin() {
            return urlLogin;
        }

        public void setUrlLogin(String urlLogin) {
            this.urlLogin = urlLogin;
        }

        public String getPathScreenshot() {
            return pathScreenshot;
        }

        public void setPathScreenshot(String pathScreenshot) {
            this.pathScreenshot = pathScreenshot;
        }

        public String getPathWebDriverChrome() {
            return pathWebDriverChrome;
        }

        public void setPathWebDriverChrome(String pathWebDriverChrome) {
            this.pathWebDriverChrome = pathWebDriverChrome;
        }
    }
}
