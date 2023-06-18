package org.fasttrackit;
import static org.fasttrackit.DemoShopTAU.CART_URL;

public class Cart {

    public void openCart() {
        System.out.println("Open " + CART_URL);
    }
    public void countItemsTotal() {
        System.out.println("Count items total price with prodcts price");
    }
    public void compareTotalPrices() {
        System.out.println("Compare total counted prices with total items prices from cart ");
    }
    public void expectedResult() {
        System.out.println("Expected result : Total prices must be the same" );
    }
    public void actualResult() {
        System.out.println("Actual result : Total prices are different");
    }
}
