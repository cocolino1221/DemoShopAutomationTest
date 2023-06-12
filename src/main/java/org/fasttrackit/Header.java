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
}
