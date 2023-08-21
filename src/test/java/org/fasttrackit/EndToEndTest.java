package org.fasttrackit;

import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.fasttrackit.dataprovider.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EndToEndTest extends TestConfig {
    Page demoShopPage;
    CartPage cartPage;
    Header header;
    Footer footer;
    CheckoutPage checkoutPage;
    SummaryOrderPage summaryPage;



    @BeforeTest
    public void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        cartPage = new CartPage();
        header = new Header();
        footer = new Footer();
        checkoutPage = new CheckoutPage();
        summaryPage = new SummaryOrderPage();


    }

    @AfterMethod
    public void cleanUp() {
        footer.reset();
        header.homePage();
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider ="ProductsDetailsDataProviderTest" )
    public void endToEndTestWithDinoUser(Product product){
        User user = new User("dino", "choochoo");
        header.clickOnTheLoginIcon();
        Modal loginModal = new Modal();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),user.getGreetingsMessage(),"When click login Hello Dino! must be displayed");
        product.addToCart();
        header.clickOnTheCartIcon();
        assertEquals(header.getCartCounter(),cartPage.getProductsCount(),"Product number in cart must be the same number like cart badge");
        assertEquals(cartPage.getCartTitle(),"Your cart","Your cart title must appear when open the cart");
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(),"Your information","Your summary text must appear when click checkout button");
        checkoutPage.clickFirstNameField();
        checkoutPage.typeOnTheFirstNameField("Dino");
        checkoutPage.clickLastName();
        checkoutPage.typeOnTheLastNameField("Turtle");
        checkoutPage.clickAdressField();
        checkoutPage.typeOnTheAdressField("Hogwarts 3/4");
        checkoutPage.continueCheckout();
        assertEquals(summaryPage.summaryPageTitle(),"Order summary","Your summary title must appear when click continue checkout");
        summaryPage.completeOrder();
        assertEquals(summaryPage.thankyouMessage(),"Thank you for your order!","Thank you message must appear when completing order");
        OrderCompletePage orderComplete = new OrderCompletePage();
        orderComplete.clickContinueShopping();
        assertEquals(demoShopPage.getTitle(),"Demo shop","When click continue shopping homepage must be displayed");
        header.clickOnTheLogoutButton();
        assertEquals(header.getGreetingsMessage(),"Hello guest!",
                "When click logout button user must logout and hello guest message must be diplayed");

    }
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider ="ProductsDetailsDataProviderTest" )
    public void endToEndTestWithTurtleUser(Product product){
        User user = new User("turtle", "choochoo");
        header.clickOnTheLoginIcon();
        Modal loginModal = new Modal();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),user.getGreetingsMessage(),"When click login Hello Turtle! must be displayed");
        product.addToCart();
        header.clickOnTheCartIcon();
        assertEquals(header.getCartCounter(),cartPage.getProductsCount(),"Product number in cart must be the same number like cart badge");
        assertEquals(cartPage.getCartTitle(),"Your cart","Your cart title must appear when open the cart");
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(),"Your information","Your summary text must appear when click checkout button");
        checkoutPage.clickFirstNameField();
        checkoutPage.typeOnTheFirstNameField("Dino");
        checkoutPage.clickLastName();
        checkoutPage.typeOnTheLastNameField("Turtle");
        checkoutPage.clickAdressField();
        checkoutPage.typeOnTheAdressField("Hogwarts 3/4");
        checkoutPage.continueCheckout();
        assertEquals(summaryPage.summaryPageTitle(),"Order summary","Your summary title must appear when click continue checkout");
        summaryPage.completeOrder();
        assertEquals(summaryPage.thankyouMessage(),"Thank you for your order!","Thank you message must appear when completing order");
        OrderCompletePage orderComplete = new OrderCompletePage();
        orderComplete.clickContinueShopping();
        assertEquals(demoShopPage.getTitle(),"Demo shop","When click continue shopping homepage must be displayed");
        header.clickOnTheLogoutButton();
        assertEquals(header.getGreetingsMessage(),"Hello guest!",
                "When click logout button user must logout and hello guest message must be diplayed");

    }
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider ="ProductsDetailsDataProviderTest" )
    public void endToEndTestWithBeetleUser(Product product){
        User user = new User("beetle", "choochoo");
        header.clickOnTheLoginIcon();
        Modal loginModal = new Modal();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),user.getGreetingsMessage(),"When click login Hello Beetle! must be displayed");
        product.addToCart();
        header.clickOnTheCartIcon();
        assertEquals(header.getCartCounter(),cartPage.getProductsCount(),"Product number in cart must be the same number like cart badge");
        assertEquals(cartPage.getCartTitle(),"Your cart","Your cart title must appear when open the cart");
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(),"Your information","Your summary text must appear when click checkout button");
        checkoutPage.clickFirstNameField();
        checkoutPage.typeOnTheFirstNameField("Dino");
        checkoutPage.clickLastName();
        checkoutPage.typeOnTheLastNameField("Turtle");
        checkoutPage.clickAdressField();
        checkoutPage.typeOnTheAdressField("Hogwarts 3/4");
        checkoutPage.continueCheckout();
        assertEquals(summaryPage.summaryPageTitle(),"Order summary","Your summary title must appear when click continue checkout");
        summaryPage.completeOrder();
        assertEquals(summaryPage.thankyouMessage(),"Thank you for your order!","Thank you message must appear when completing order");
        OrderCompletePage orderComplete = new OrderCompletePage();
        orderComplete.clickContinueShopping();
        assertEquals(demoShopPage.getTitle(),"Demo shop","When click continue shopping homepage must be displayed");
        header.clickOnTheLogoutButton();
        assertEquals(header.getGreetingsMessage(),"Hello guest!",
                "When click logout button user must logout and hello guest message must be diplayed");

    }
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider ="ProductsDetailsDataProviderTest" )
    public void endToEndTestWithLockedUser(Product product){
        User user = new User("locked", "choochoo");
        header.clickOnTheLoginIcon();
        Modal loginModal = new Modal();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),user.getGreetingsMessage(),"When click login Hello Locked! must be displayed");
        product.addToCart();
        header.clickOnTheCartIcon();
        assertEquals(header.getCartCounter(),cartPage.getProductsCount(),"Product number in cart must be the same number like cart badge");
        assertEquals(cartPage.getCartTitle(),"Your cart","Your cart title must appear when open the cart");
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(),"Your information","Your summary text must appear when click checkout button");
        checkoutPage.clickFirstNameField();
        checkoutPage.typeOnTheFirstNameField("Dino");
        checkoutPage.clickLastName();
        checkoutPage.typeOnTheLastNameField("Turtle");
        checkoutPage.clickAdressField();
        checkoutPage.typeOnTheAdressField("Hogwarts 3/4");
        checkoutPage.continueCheckout();
        assertEquals(summaryPage.summaryPageTitle(),"Order summary","Your summary title must appear when click continue checkout");
        summaryPage.completeOrder();
        assertEquals(summaryPage.thankyouMessage(),"Thank you for your order!","Thank you message must appear when completing order");
        OrderCompletePage orderComplete = new OrderCompletePage();
        orderComplete.clickContinueShopping();
        assertEquals(demoShopPage.getTitle(),"Demo shop","When click continue shopping homepage must be displayed");
        header.clickOnTheLogoutButton();
        assertEquals(header.getGreetingsMessage(),"Hello guest!",
                "When click logout button user must logout and hello guest message must be diplayed");

    }
}
