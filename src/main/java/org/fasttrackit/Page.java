package org.fasttrackit;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import net.bytebuddy.TypeCache;
import net.bytebuddy.description.type.TypeDefinition;


import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class Page {
    public static final String HOMEPAGE_URL = "https://fasttrackit-test.netlify.app/#/";
    private final SelenideElement modalDialog = $(".modal-dialog");
    private final SelenideElement searchBar = $("#input-search");
    private final SelenideElement searchBtn = $(".btn-light");
    private final SelenideElement sortBar = $(".sort-products-select");



    private final List<Product> productList = new ArrayList<>();


    public boolean isModalDialogDisplayed() {
        return modalDialog.isDisplayed();
    }

    public void openPage() {
        Selenide.open("https://fasttrackit-test.netlify.app/#/");

    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getTitle() {
        return Selenide.title();
    }

    public boolean setSearchBar() {
        return !searchBar.isDisplayed();
    }

    public boolean searchBarIsEnabled() {
        return !searchBar.isEnabled();
    }

    public void searchBarClick() {
        searchBar.click();
    }

    public boolean sortBarIsDisplayed() {
        return !sortBar.isDisplayed();
    }

    public boolean sortBarIsEnabled() {
        return sortBar.isEnabled();
    }

    public void searchButtonClick() {
        searchBtn.click();
    }

    public void typeOnTheSearchBar(String wordToType) {
        searchBar.sendKeys(wordToType);
    }

    List<Product> products = new ArrayList<Product>();
    Product firstProduct = new Product("1", "Awesome Granite Chips", "15.99");
    Product lastProduct = new Product("0", "Refined Frozen Mouse", "9.99");
    Product lowestPriceProduct = new Product("6", "Practical Wooden Bacon", "1.99");
    Product highestPriceProduct = new Product("4", "Practical Wooden Bacon", "29.99");

    public List<Product> getProducts() {
        return products;
    }

    public boolean firstProductDisplayAToZ() {
        return firstProduct.equals(0) && lastProduct.equals(9);
    }

    public boolean lastProductDisplayFirstZToA() {
        return lastProduct.equals(0) && firstProduct.equals(9);
    }

    public boolean lowestPriceFirst() {
        return lowestPriceProduct.equals(0) && highestPriceProduct.equals(9);
    }

    public boolean highestPriceFirst() {
        return highestPriceProduct.equals(0) && lowestPriceProduct.equals(9);
    }

}

