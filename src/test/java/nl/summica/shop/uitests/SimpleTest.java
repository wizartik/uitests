package nl.summica.shop.uitests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import nl.summica.shop.uitests.util.ShopParametrizedTest;
import nl.summica.shop.uitests.util.selenium.TestedPageContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class SimpleTest {

    @ShopParametrizedTest
    void openAllStoresHomePageAndSearchProducts(TestedPageContext ctx) {
        ctx.openRootPage();
        boolean hasHeader = $(By.className("js-mainHeader")).exists();
        boolean hasFooter = $(".footer__top").exists();

        SelenideElement searchInput = $(".js-site-search-input");
        searchInput.setValue("123");
        searchInput.pressEnter();

//      #page2
        SelenideElement searchResultElement = $(".product-list-right-component");
        searchResultElement.waitUntil(Condition.exist, 15_000);
        boolean searchPageWorks = searchResultElement.exists();

        Assertions.assertAll(
                () -> assumeTrue(hasHeader, "has header"),
                () -> assumeTrue(hasFooter, "has footer"),
                () -> assumeTrue(searchPageWorks, "has search page")
        );
    }

    @Disabled
    @Test
    void plainSelenideTestWithoutCustomizations_chrome() {
        open("https://github.com/selenide-examples");
        Assertions.assertTrue($("img").exists(), "page should have an image");
    }


//    @Disabled
//    @ShopParametrizedTest(browsers = DriverFactory.Browser.FIREFOX, paths = "/not used in this test")
//    void accessPlainSeleniumWebDriver_firefox(TestedPageContext ctx) {
//        //I think it's simplier to write tests using Selenide wrapper, but plain selenium although works
//        WebDriver driver = WebDriverRunner.webdriverContainer.getSelenideDriver().getWebDriver();
//        driver.get("https://examples.javacodegeeks.com/enterprise-java/selenium/selenium-tutorial-for-beginners/");
//        WebElement anElement = driver.findElement(By.id("syntaxhighlighteranchor"));
//        Assertions.assertNotNull(anElement, "element should not be null");
//    }


}
