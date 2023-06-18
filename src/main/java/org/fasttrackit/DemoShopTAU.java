package org.fasttrackit;

public class DemoShopTAU {
    public static final String HOMEPAGE_URL = "https://fasttrackit-test.netlify.app/#/";

    public static final String CART_URL = "https://fasttrackit-test.netlify.app/#/cart";

    public static void main(String[] args) {
        Page demoShopPage = new Page();
        demoShopPage.openPage();

        Header header = new Header();
        String greetingsMsg = header.getGreetingsMessage();

        Cart cartPage = new Cart();
        cartPage.openCart();
        System.out.println("Open " + CART_URL);


// Check Login function
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
        String loginMsg = "Hi Dino!";
        header.expectedResult(greetingsMsg);


// Add product to cart from homepage test.


        demoShopPage.openPage();
        demoShopPage.chooseAnyProductFromPage();
        demoShopPage.addToCart();
        header.expectedResult();
        header.actualResult();

//Check logout function

        demoShopPage.openPage();
        header.clickLogoutIcon();
        header.expectedResultLogout(greetingsMsg);
        header.actualResultLogout(greetingsMsg);


// Check if product price amount is calculated corectly in cart

        demoShopPage.openPage();
        demoShopPage.addProductToCart();
        cartPage.openCart();
        cartPage.countItemsTotal();
        cartPage.compareTotalPrices();
        cartPage.expectedResult();
        cartPage.actualResult();



    }




}