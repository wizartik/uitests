package nl.summica.shop.uitests.util.selenium;

import com.codeborne.selenide.WebDriverRunner;
import nl.summica.shop.uitests.util.selenium.DriverFactory.Browser;

//represents data for a test (server url + store name + todo: products for store + user for store + ...)
public class TestedPageContext {
    private String serverUrl;
    private String path;
    private Browser browser;
    private String testMethodName;
    private String userLogin;
    private String userPassword;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public String getTestMethodName() {
        return testMethodName;
    }

    public void setTestMethodName(String testMethodName) {
        this.testMethodName = testMethodName;
    }

    public void openRootPage() {
        WebDriverRunner.webdriverContainer.getSelenideDriver().open(serverUrl + path);
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "TestedPageContext{" +
                "serverUrl='" + serverUrl + '\'' +
                ", path='" + path + '\'' +
                ", browser=" + browser +
                ", testMethodName='" + testMethodName + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
