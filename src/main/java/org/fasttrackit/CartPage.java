package org.fasttrackit;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class CartPage {

    private final SelenideElement cartTitle = $(".subheader-container");
    private final SelenideElement greetingsMessage = $(".text-center");
    private final List<ProductInCart> productsInCarts = new ArrayList<>();
    private final SelenideElement rowNumbers = $(".row-cols-md-1 .container");
    private final SelenideElement continueShoppingButton = $(".btn-danger");
    private final SelenideElement checkoutButton = $(".btn-success");
    private final ElementsCollection products = $$(".row .row");
    private final SelenideElement totalPrice = $(".amount-total .amount");


    public String getCartTitle() {
        return cartTitle.text();
    }

    public String getGreetingsMessage() {
        return greetingsMessage.getText();
    }

    public void withProduct(Product p) {
        ProductInCart pic = new ProductInCart(p);
        productsInCarts.add(pic);
    }
    public List<ProductInCart> getProductsInCarts(){
        return this.productsInCarts;
    }
    public void checkoutButton(){
        checkoutButton.click();
    }
    public void continueShoppingButton(){
        continueShoppingButton.click();
    }
    public String getProductsCount(){
        return String.valueOf (products.size());
    }



    public String totalPrice() {
        return totalPrice.getText().replaceAll("\\$","");
    }

    public void compareTotalPrices() {
        System.out.println("Compare total counted prices with total items prices from cart ");
    }
    public String getTotal(Product product1, Product product2) {
        ProductInCart productInCart = new ProductInCart(product1);
        ProductInCart secondProductInCart = new ProductInCart(product2);
        String firstArticlePrice = productInCart.getTotalProductPrice();
        String secondArticlePrice = secondProductInCart.getTotalProductPrice();
        String firstPrice = String.valueOf(firstArticlePrice.replaceAll("\\$",""));
        String secondPrice = String.valueOf(secondArticlePrice.replaceAll("\\$",""));
        String total = firstPrice + secondPrice;
        return total;
    }

}
