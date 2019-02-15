package nl.summica.shop.uitests;

import com.codeborne.selenide.Configuration;
import nl.summica.shop.uitests.pageObjects.CartPage;
import nl.summica.shop.uitests.pageObjects.ResultSearchPage;
import nl.summica.shop.uitests.pageObjects.SearchPage;
import nl.summica.shop.uitests.util.ShopParametrizedTest;
import nl.summica.shop.uitests.util.JsErrorsChecker;
import nl.summica.shop.uitests.util.selenium.TestedPageContext;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

public class SearchAndCartTest {

    private final String SEARCH_VALUE = "product";
    private final Integer PRODUCTS_ADDED_TO_CART = 3;

    @BeforeAll
    static void setUp() {
        Configuration.timeout = 8000;
    }

    @ShopParametrizedTest
    void searchAndCartTest(TestedPageContext ctx) {
        ctx.openRootPage();

        SearchPage searchPage = new SearchPage();
        searchPage.search(SEARCH_VALUE);

        ResultSearchPage resultSearchPage = new ResultSearchPage();
        resultSearchPage.verifySearch(SEARCH_VALUE);

        List<String> addedProducts = resultSearchPage.addProductsToCart(PRODUCTS_ADDED_TO_CART);
        resultSearchPage.verifyProductsQnt(PRODUCTS_ADDED_TO_CART);

        CartPage cartPage = new CartPage();
        cartPage.verifyProductsQnt(PRODUCTS_ADDED_TO_CART);
        cartPage.verifyAddedProducts(addedProducts);

        JsErrorsChecker.checkJSErrors();
    }
}
