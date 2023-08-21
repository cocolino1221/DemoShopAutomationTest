package org.fasttrackit;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        return modalDialog.exists() && modalDialog.isDisplayed();
    }

    public void openPage() {
        System.out.println("Open " + HOMEPAGE_URL);
        open(HOMEPAGE_URL);

    }
    public List<Product> getProductList(){
        return productList;
    }

    public String getTitle() {
        return Selenide.title();
    }

    public boolean setSearchBar(){
        return !searchBar.isDisplayed();
    }
    public boolean searchBarIsEnabled(){
        return !searchBar.isEnabled();
    }
    public void searchBarClick(){
        searchBar.click();
    }
    public boolean sortBarIsDisplayed(){
        return !sortBar.isDisplayed();
    }
    public boolean sortBarIsEnabled(){
        return sortBar.isEnabled();
    }
    public void searchButtonClick(){
        searchBtn.click();
    }
    public void typeOnTheSearchBar(String wordToType) {
        searchBar.sendKeys(wordToType);
    }
    List<Product> products = new ArrayList<Product>();
        Product firstProduct = new Product("1","Awesome Granite Chips","15.99");
        Product lastProduct = new Product("0","Refined Frozen Mouse", "9.99");
        public Product getFirstProduct(){
            return firstProduct;
        }
        public Product getLastProduct(){
            return lastProduct;
        }

    public List<Product> getProducts() {
        return products;
    }
}

