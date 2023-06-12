package org.fasttrackit;

public class DemoShopTAU {
    public static final String HOMEPAGE_URL = "https://fasttrackit-test.netlify.app/#/";

    public static void main(String[] args) {
        Page demoShopPage = new Page();
        demoShopPage.openPage();

        Header header = new Header();
        String greetingsMsg = header.getGreetingsMessage();

        System.out.println(greetingsMsg + " message displayed in header");

        header.clickOnTheLoginButton();

        Modal loginModal = new Modal();
        loginModal.clickOnTheUsernameField();
        String username = "dino";
        loginModal.typeInUsername(username);
        loginModal.clickOnThePasswordField();
        String password = "choochoo";
        loginModal.typeInPassword(password);

        loginModal.clickOnTheLoginButton();
        String greetingsMsg = "Hi Dino!";
        header.expectedResult(greetingsMsg);
    }


}