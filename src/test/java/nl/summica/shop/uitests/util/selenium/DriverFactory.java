package nl.summica.shop.uitests.util.selenium;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;

public class DriverFactory {

    public enum Browser {
        CHROME(Browsers.CHROME), FIREFOX(Browsers.FIREFOX);
        private String code;

        Browser(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public static SelenideDriver getDriver(Browser browser) {
        SelenideConfig config = new SelenideConfig();
        config.browser(browser.getCode());
        return new SelenideDriver(config);
    }


}
