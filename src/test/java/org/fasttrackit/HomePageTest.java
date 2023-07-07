package org.fasttrackit;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.TypeInfo;

import java.lang.reflect.Type;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;


public class HomePageTest {

    Page demoShopPage;
    Modal loginModal;
    CartPage cartPage;

    @BeforeTest
    private void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
    }

    @Test
    public void whenOpeningDemoShopPageTitleIsDemoshop() {
        String title = demoShopPage.getTitle();
        assertEquals(title, "Demo shop", "Expected title to be Demo Shop");

    }

    @Test
    public void whenOpeningPageHelloGuestMsgIsDisplayed() {
        Header header = new Header();
        String greetingsMsg = header.getGreetingsMessage();
        assertEquals(greetingsMsg, "Hello guest!", "When opening page, Hello guest! greetings message must be displayed");


    }

    @Test
    public void clickOnTheLoginButtonFromHeaderToOpenTheLoginModal() {
        Modal loginModal = new Modal();
        $(".fa-sign-in-alt").click();
        String modalTitle = loginModal.getModalTitle();
        assertEquals(modalTitle, "Login", "Login message must appear");
    }

    @Test
    public void checkLoginFunctionWithValidUsernameAndPassword() {
        Modal loginModal = new Modal();
        $(".fa-sign-in-alt").click();
        String modalTitle = loginModal.getModalTitle();
        $("#user-name").setValue("dino");
        sleep(2 * 1000);
        $("#password").setValue("choochoo");
        sleep(2 * 1000);
        $(".modal-content .btn-primary").click();
        sleep(2 * 1000);
        Header header= new Header();
        String greetingsMsg = header.getGreetingsMessage();
        assertEquals(greetingsMsg,"Hi dino!");

    }
    @Test
    public void checkLogoutFunction() {
        Modal loginModal = new Modal();
        $(".fa-sign-in-alt").click();
        String modalTitle = loginModal.getModalTitle();
        $("#user-name").setValue("dino");
        sleep(2 * 1000);
        $("#password").setValue("choochoo");
        sleep(2 * 1000);
        $(".modal-content .btn-primary").click();
        sleep(2 * 1000);
        Header header= new Header();
        $(".fa-sign-out-alt").click();



    }

    @Test
    public void clickOnTheCartButtonToOpenCartPage() {
        CartPage cartPage = new CartPage();
        cartPage.cartButton();
        String cartTitle = cartPage.getCartTitle();
        sleep(1 * 1000);
        assertEquals(cartTitle, "Your cart");

    }
    @Test
    public void addProductToCart(){
        demoShopPage.openPage();
        demoShopPage.chooseOneProductFromPage();
        demoShopPage.addToCart();
    }
    @Test
    public void openAwesomeGraniteChipsPage(){
        demoShopPage.openPage();
        $("[href='#/product/1']").click();
        sleep(5 * 1000);
    }
}
