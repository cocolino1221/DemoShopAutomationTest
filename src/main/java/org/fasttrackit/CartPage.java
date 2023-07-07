package org.fasttrackit;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.files.DownloadActions.click;
import static org.fasttrackit.DemoShopTAU.CART_URL;

public class CartPage {
    private final SelenideElement cartTitle = $(".subheader-container");
    public void cartButton() {
        $(".navbar-nav [href='#/cart']").click();
    }

    public String getCartTitle() {
        return cartTitle.text();

    }

    private final String greetingsMessage = "How about adding some products in your cart?";

    public String getGreetingsMessage() {
        return greetingsMessage;
    }

    private final List<ProductInCart> productsInCarts = new ArrayList<>();

    public void withProduct(ProductInCart product) {
        productsInCarts.add(product);
    }

    public List<ProductInCart> getProductsInCarts() {
        return productsInCarts;
    }


    public void openCart() {
        $(".fa-shopping-cart").click();
    }

    public void countItemsTotal() {
        System.out.println("Count items total price with prodcts price");
    }

    public void compareTotalPrices() {
        System.out.println("Compare total counted prices with total items prices from cart ");
    }

    public void expectedResult() {
        System.out.println("Expected result : Total prices must be the same");
    }

    public void actualResult() {
        System.out.println("Actual result : Total prices are different");
    }
}
