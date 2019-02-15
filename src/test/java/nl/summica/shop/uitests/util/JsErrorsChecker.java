package nl.summica.shop.uitests.util;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import nl.summica.shop.uitests.util.selenium.DriverFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsErrorsChecker {

    private static final Logger LOG = Logger.getLogger(JsErrorsChecker.class.getName());

    public static void checkJSErrors() {
        if (isFirefox()) {
            printFirefoxLog();
            return;
        }

        List<String> errors = getDriverLogs(Level.SEVERE);
        if (!errors.isEmpty()){
            printErrors(errors);
        }
    }

    private static boolean isFirefox(){
        return getBrowserName().equalsIgnoreCase(DriverFactory.Browser.FIREFOX.getCode());
    }

    private static String getBrowserName() {
        Capabilities cap = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getCapabilities();
        return cap.getBrowserName().toLowerCase();
    }

    private static void printFirefoxLog() {
        LOG.warning("Console logging is disabled in firefox");
    }

    private static void printErrors(List<String> errors) {
        LOG.warning(formErrorsMessage(errors));
    }

    private static String formErrorsMessage(List<String> errors){
        StringBuilder completeMessage = new StringBuilder();
        completeMessage.append(errors.size());
        completeMessage.append(" javascript errors have occurred:\n");
        appendAllMessages(completeMessage, errors);
        return completeMessage.toString();
    }

    public static List<String> getDriverLogs(Level level){
        return Selenide.getWebDriverLogs(LogType.BROWSER, level);
    }

    private static void appendAllMessages(StringBuilder completeMessage, List<String> errors) {
        for (String error : errors) {
            completeMessage.append(error);
            completeMessage.append("\n");
        }
    }
}
