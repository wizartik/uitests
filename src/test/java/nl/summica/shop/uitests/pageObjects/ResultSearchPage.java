package nl.summica.shop.uitests.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ResultSearchPage {

    private SelenideElement searchResultHeading;

    private ElementsCollection addToCartButtons;

    private SelenideElement productsQntElement;

    private ElementsCollection productNames;

    public ResultSearchPage() {
        this.searchResultHeading = $(".results h1");
        this.addToCartButtons = $$(".add_to_cart_form [type=submit]");
        this.productsQntElement = $(".desktop__nav .nav-items-total");
        this.productNames = $$(".product__list--name");
    }

    public SelenideElement getSearchResultHeading() {
        return searchResultHeading;
    }

    public void setSearchResultHeading(SelenideElement searchResultHeading) {
        this.searchResultHeading = searchResultHeading;
    }

    public ElementsCollection getAddToCartButtons() {
        return addToCartButtons;
    }

    public void setAddToCartButtons(ElementsCollection addToCartButtons) {
        this.addToCartButtons = addToCartButtons;
    }

    public SelenideElement getProductsQntElement() {
        return productsQntElement;
    }

    public void setProductsQntElement(SelenideElement productsQntElement) {
        this.productsQntElement = productsQntElement;
    }

    public ElementsCollection getProductNames() {
        return productNames;
    }

    public void setProductNames(ElementsCollection productNames) {
        this.productNames = productNames;
    }

    public void verifySearch(String searchText) {
        searchResultHeading.should(Condition.text(searchText));
    }

    private List<String> getLinksFromProductNames(ElementsCollection elements, int qnt){
        List<String> links = new ArrayList<>(qnt);

        for (int i = 0; i < qnt; i++) {
            links.add(elements.get(i).getAttribute("href"));
        }

        return links;
    }

    /**
     * @param qnt
     * @return list of hrefs to added products
     */
    public List<String> addProductsToCart(int qnt) {
        IntStream.range(0, qnt).mapToObj(addToCartButtons::get).forEach(this::addProductToCart);
        return getLinksFromProductNames(productNames, qnt);
    }

    private void addProductToCart(SelenideElement element) {
//        click addToCart
        element.scrollTo();
        element.click();
//      click "continue shopping"
        $(".btn.btn-default.btn-block.js-mini-cart-close-button").click();
    }

    public void verifyProductsQnt(int qnt){
        productsQntElement.scrollTo();
        productsQntElement.shouldHave(text(String.valueOf(qnt)));
    }
}
