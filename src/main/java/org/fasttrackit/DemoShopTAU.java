package org.fasttrackit;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class DemoShopTAU {
    public static final String HOMEPAGE_URL = "https://fasttrackit-test.netlify.app/#/";

    public static final String CART_URL = "https://fasttrackit-test.netlify.app/#/cart";

    public static void main(String[] args) {
        Page demoShopPage = new Page();
        demoShopPage.openPage();

        Header header = new Header();
        String greetingsMsg = header.getGreetingsMessage();

        CartPage cartPage = new CartPage();
        cartPage.openCart();
        System.out.println("Open " + CART_URL);


// Check Login function
        System.out.println(greetingsMsg + " message displayed in header");

        header.clickOnTheLoginIcon();


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


// Add one product to cart from homepage test


        demoShopPage.openPage();
        demoShopPage.chooseOneProductFromPage();
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


        System.out.println("--------------");
        System.out.println("Add one product to cart");
// Add one product to cart
// Add Awesome Granite Chips

        Product awesomeGraniteChips = new Product("Awesome Granite Chips", 15.99);

        String name = awesomeGraniteChips.getName();
        System.out.println("Product is: " + name);
        awesomeGraniteChips.addToCart();
        String counter = header.getCartCounter();
        System.out.println("Header cart counter is : " + counter );
        System.out.println("Expected result: Header count is 1 " );


// Delete product from cart
        System.out.println("--------------");
        System.out.println("Delete product from cart");
        header.clickOnTheCartIcon();
        ProductInCart productInCart = new ProductInCart(1,awesomeGraniteChips);
        cartPage.withProduct(productInCart);
        cartPage.withProduct(productInCart1);
        ProductInCart productInCart2;
        cartPage.withProduct(productInCart2);

        ProductInCart deletedProductFromCart = cartPage.getProductInCarts().get(0);
        deletedProductFromCart.clickOnTheDeleteFromCart();


    }


}