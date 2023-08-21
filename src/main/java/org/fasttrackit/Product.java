package org.fasttrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Product {
    private final SelenideElement link;
    private final SelenideElement parentCard;
    private final String name;
    private final String price;
    private final SelenideElement addToCartButton;
    private final SelenideElement addToFavButton;
    private final SelenideElement deleteFavoriteProduct;
    private final SelenideElement deleteFromCart;
    private final String productId;

    public Product(String productId ,String name , String price) {
        this.productId = productId;
        String productLink = String.format("[href='#/product/%s']", productId);
        this.link = $(productLink);
        this.name = link.getText();
        this.parentCard = link.parent().parent();
        this.price = this.parentCard.$(".card-footer span").getText();
        this.addToCartButton = this.parentCard.$(".fa-cart-plus");
        this.addToFavButton = this.parentCard.$(".fa-heart");
        this.deleteFavoriteProduct = this.parentCard.$(".fa-heart-broken");
        this.deleteFromCart = this.parentCard.$(".fa-trash");
    }


    public String getProductId() {
        return productId;
    }

    public void addToCart() {
        this.addToCartButton.click();

    }

    public void addToFav() {
        this.addToFavButton.click();
    }

    public String getName() {
        return name;

    }

    public String getPrice() {
        return price;
    }

    public void deleteFavProduct() {
        this.deleteFavoriteProduct.click();
    }
    public void deleteButton() {
        this.deleteFromCart.click();

    }
    public void productPage (){
        this.link.click();
    }

}
