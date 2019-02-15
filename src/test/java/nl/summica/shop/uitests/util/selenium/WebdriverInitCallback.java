package nl.summica.shop.uitests.util.selenium;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.TestTemplateInvocationTestDescriptor;
import org.openqa.selenium.WebDriver;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class WebdriverInitCallback implements BeforeEachCallback, AfterEachCallback {


    @Override
    public void beforeEach(ExtensionContext ctx) {
        //create webdriver for given browser before test invocation
        //reason: selenide OOTB suppports only one browser per application
        //I tuned it to support multiple ones
        TestedPageContext pageContext = getPageContext(ctx);
        SelenideDriver selenideLazyDriver = DriverFactory.getDriver(pageContext.getBrowser());
        WebDriver seleniumDriver = selenideLazyDriver.getAndCheckWebDriver();
        WebDriverRunner.setWebDriver(seleniumDriver);
    }

    @Override
    public void afterEach(ExtensionContext ctx) {
        WebDriverRunner.closeWebDriver();
    }

    //a hack. maybe, in future junit releases it will not be needed
    private TestedPageContext getPageContext(ExtensionContext extensionContext) {
        TestTemplateInvocationTestDescriptor testDescriptor = (TestTemplateInvocationTestDescriptor) ReflectionTestUtils.getField(extensionContext, "testDescriptor");
        TestTemplateInvocationContext invocationContext = (TestTemplateInvocationContext) ReflectionTestUtils.getField(testDescriptor, "invocationContext");
        Object[] arguments = (Object[]) ReflectionTestUtils.getField(invocationContext, "arguments");
        return (TestedPageContext) Arrays.stream(arguments).filter(o -> o instanceof TestedPageContext).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Test method should contain TestedPageContext parameter for test data injection"));
    }
}
