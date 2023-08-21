package org.fasttrackit;


import com.codeborne.selenide.Selenide;
import org.fasttrackit.config.TestConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;


public class HomePageTest extends TestConfig {
    Page demoShopPage;
    Header header;
    Footer footer;

    @BeforeTest
    public void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        header = new Header();
        footer = new Footer();

    }
    @AfterMethod
    public void cleanUp(){
        footer.reset();
        header.homePage();
        Selenide.refresh();
    }
    @Test
    public void whenOpeningDemoShop_PageTitleIsDemoshop() {
        String title = demoShopPage.getTitle();
        assertEquals(title, "Demo shop", "Expected title to be Demo Shop");

    }

    @Test
    public void whenOpeningPage_HelloGuestMsgIsDisplayed() {
        Header header = new Header();
        String greetingsMsg = header.getGreetingsMessage();
        assertEquals(greetingsMsg, "Hello guest!", "When opening page, Hello guest! greetings message must be displayed");

    }

    @Test
    public void clickOnTheCartButtonToOpenCartPage() {
        CartPage cartPage = new CartPage();
        header.clickOnTheCartIcon();
        String cartTitle = cartPage.getCartTitle();
        assertEquals(cartTitle, "Your cart");

    }



}
