package org.fasttrackit;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Header {
    private final SelenideElement cartIcon = $(".fa-shopping-cart");
    private final SelenideElement cartCounter = $(".shopping_cart_badge");
    private final SelenideElement favoriteIcon = $(".fa-heart");
    private final ElementsCollection missingFavBadge = $$(".fa-heart+.shopping_cart_badge");
    private final SelenideElement favCounter = $(".fa-heart+.shopping_cart_badge");
    private final SelenideElement loginButton = $(".fa-sign-in-alt");
    private final SelenideElement logoutButton = $(".fa-sign-out-alt");
    private final ElementsCollection missingCartBadge = $$(".shopping_cart_badge");
    private final SelenideElement homePageLogo = $(".brand-logo");

    public String getGreetingsMessage() {
        SelenideElement greetingsMessage = $(".navbar-text span span");
        return greetingsMessage.text();

    }
    

    public void clickOnTheLoginIcon() {
        loginButton.click();

    }
    public void clickOnTheLogoutButton(){
        logoutButton.click();
    }


    public void clickOnTheCartIcon() {
       cartIcon.click();
    }



    public String getCartCounter() {
        return cartCounter.getText();
    }

    public SelenideElement getIconCart(){
        return cartIcon;
    }
    public String getFavCounter(){
        return favCounter.getText();
    }
    public SelenideElement getFavoriteIcon(){
        return favoriteIcon;
    }
    public boolean isFavBadgeDisplayed(){
        return !missingFavBadge.isEmpty();

    }
    public boolean isCartBadgeDisplayed(){
        return !missingCartBadge.isEmpty();
    }
    public void homePage(){
        homePageLogo.click();
    }


}
