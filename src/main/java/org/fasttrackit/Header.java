package org.fasttrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Header {
    private final SelenideElement cartIcon = $(".fa-shopping-cart");
    private final String cartCounter = "1";
    private final String loginIcon = "Login icon";

    private SelenideElement loginButton;

    public String getGreetingsMessage() {
        SelenideElement greetingsMessage = $(".navbar-text span span");
        return greetingsMessage.text();
    }
    

    public void clickOnTheLoginIcon() {
        loginButton = $(".fa-sign-in-alt");
        loginButton.click();

    }


    public void clickOnTheCartIcon() {
        System.out.println("Click on the " + cartIcon + "from header");
    }


    public void expectedResult(String greetingsMsg) {
        System.out.println("Expected result : " + greetingsMsg + " message displayed in header");
    }

    public void expectedResult() {
        System.out.println("Expected result : product added to cart , an red dot must appear on header cart icon");
    }

    public void actualResult() {
        System.out.println("Actual result : product is added to cart and red dot appear on header cart icon");
    }

    public void clickLogoutIcon() {
        System.out.println("Click on the logout icon from header top right side");
    }

    public void expectedResultLogout(String greetingsMsg) {
        System.out.println("Expected result : " + greetingsMsg + " appear next to the login icon");
    }

    public void actualResultLogout(String greetingsMsg) {
        System.out.println("Actual result : " + greetingsMsg + "appear next to the login icon ");
    }

    public String getCartCounter() {
        return cartCounter;
    }
    public void click(SelenideElement $) {
    }
    public SelenideElement getIconCart(){
        return cartIcon;
    }






}
