package org.fasttrackit;

import static org.fasttrackit.DemoShopTAU.HOMEPAGE_URL;

public class Page {
    public void openPage() {
        System.out.println("Open " + HOMEPAGE_URL);

    }

    public void chooseAnyProductFromPage() {
        System.out.println("Choose any product from page");
    }

    public void addToCart() {
        System.out.println("Click on the Add to cart icon");
    }
    public void addProductToCart() {
        System.out.println("Add minimum two product to cart");
    }
}

