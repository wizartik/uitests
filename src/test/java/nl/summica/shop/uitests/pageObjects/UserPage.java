package nl.summica.shop.uitests.pageObjects;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserPage {

    private SelenideElement accountNavigation;
    private ElementsCollection navigationLinks;

    public UserPage() {
        this.accountNavigation = $(".navigation.navigation--top .myAccountLinksHeader");
        this.navigationLinks = $$(".navigation.navigation--top .nav-link a");
    }

    public void goToPersonalDetailsPage() {
        accountNavigation.click();
        getNavigationElementByTitle("Personal Details").click();
    }

    public void goToEmailPage() {
        accountNavigation.click();
        getNavigationElementByTitle("Email Address").click();
    }

    public SelenideElement getNavigationElementByTitle(String title) {
        return navigationLinks.filter(Condition.attribute("title", title)).first();
    }
}
