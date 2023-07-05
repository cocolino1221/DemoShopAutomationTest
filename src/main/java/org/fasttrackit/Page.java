package org.fasttrackit;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.open;
import static org.fasttrackit.DemoShopTAU.HOMEPAGE_URL;

public class Page {
    public void openPage() {
        System.out.println("Open " + HOMEPAGE_URL);
        open(HOMEPAGE_URL);

    }

    public String getTitle(){
        return Selenide.title();
    }

    public void chooseOneProductFromPage() {
        System.out.println("Choose any product from page");
    }

    public void addToCart() {
        System.out.println("Click on the Add to cart icon");
    }
    public void addProductToCart() {
        System.out.println("Add minimum two product to cart");
    }
}

