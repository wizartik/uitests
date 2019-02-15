package nl.summica.shop.uitests.pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SearchPage {
    private SelenideElement searchBox;

    public SearchPage() {
        this.searchBox = $(".js-site-search-input");
    }

    public SelenideElement getSearchBox() {
        return searchBox;
    }

    public void setSearchBox(SelenideElement searchBox) {
        this.searchBox = searchBox;
    }

    public void search(String text) {
        searchBox.clear();
        searchBox.setValue(text);
        searchBox.pressEnter();
    }
}
