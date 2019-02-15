package nl.summica.shop.uitests.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class CartPage {

    private SelenideElement cartButton;

    private ElementsCollection shownProducts;

    public CartPage() {
        this.cartButton = $(".desktop__nav .nav-items-total");
        this.cartButton.click();
        this.shownProducts = $(".item__list--cart.item__list.item__list__cart").findAll("table");
    }

    public SelenideElement getCartButton() {
        return cartButton;
    }

    public void setCartButton(SelenideElement cartButton) {
        this.cartButton = cartButton;
    }

    public ElementsCollection getShownProducts() {
        return shownProducts;
    }

    public void setShownProducts(ElementsCollection shownProducts) {
        this.shownProducts = shownProducts;
    }

    public void goToCartPage() {
        cartButton.click();
    }

    public void verifyProductsQnt(int qnt) {
        shownProducts.shouldHaveSize(qnt);
    }

    public void verifyAddedProducts(List<String> links) {

        for (int i = 0; i < shownProducts.size(); i++) {
            SelenideElement product = shownProducts.get(i);

            String realHref = product.find(".item__info a").getAttribute("href");
            String givenHref = links.get(links.size() - 1 - i);
            Assertions.assertEquals(givenHref, realHref);
        }
    }

}
