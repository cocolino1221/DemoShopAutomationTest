package org.fasttrackit;


import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;
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
    public void clickOnTheLoginButton() {
        Modal loginModal = new Modal();
        $(".fa-sign-in-alt").click();
        String modalTitle = loginModal.getModalTitle();
        sleep(5 * 1000);
        assertEquals(modalTitle, "Login", "Login message must appear");

    }

    @Test
    public void openCartPage() {
        CartPage cartPage = new CartPage();
        String cartTitle = cartPage.getCartTitle();
        cartPage.openCart();
        sleep(5 * 1000);

        assertEquals(cartTitle, "Your cart");
    }
}
