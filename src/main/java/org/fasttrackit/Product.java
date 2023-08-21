package org.fasttrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Product {
    private final SelenideElement link;
    private final String name;
    private final String price;
    private final SelenideElement addToCartButton;
    private final SelenideElement addToFavButton;
    private final SelenideElement deleteFavoriteProduct;
    private final SelenideElement deleteFromCart;
    private final String productId;
    private final SelenideElement productPicture;

    public Product(String productId ,String name , String price) {
        this.productId = productId;
        String productLink = String.format("[href='#/product/%s']", productId);
        this.link = $(productLink);
        this.name = link.getText();
        SelenideElement parentCard = link.parent().parent();
        this.price = parentCard.$(".card-footer span").getText();
        this.addToCartButton = parentCard.$(".fa-cart-plus");
        this.addToFavButton = parentCard.$(".fa-heart");
        this.deleteFavoriteProduct = parentCard.$(".fa-heart-broken");
        this.deleteFromCart = parentCard.$(".fa-trash");
        this.productPicture = parentCard.$(".card-img");
    }
    public boolean validatePriceExist(){
        return !this.price.isEmpty();
    }
    public boolean validateAddToCartButtonExist(){
       return this.addToCartButton.isDisplayed() && this.addToCartButton.isEnabled();
    }
    public boolean validateAddtoFavButtonExist(){
        return  this.addToFavButton.isDisplayed() && this.addToFavButton.isEnabled();
    }
    public boolean validateDeleteFromFavExist(){
        return this.deleteFavoriteProduct.isDisplayed() && this.deleteFavoriteProduct.isEnabled();
    }
    public boolean validatePictureIsDisplayed(){
        return this.productPicture.isDisplayed()&& this.productPicture.isImage();
    }
    public boolean validateProductTitleExist(){
        return this.name.isEmpty();
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
