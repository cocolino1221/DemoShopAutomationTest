package org.fasttrackit;

public class Header {
    public String getGreetingsMessage() {
        String getGreetingsMsg = "Hello guest!";
        return getGreetingsMsg;
    }
    public void clickOnTheLoginButton() {
        System.out.println("Click on the Login button");
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
}
