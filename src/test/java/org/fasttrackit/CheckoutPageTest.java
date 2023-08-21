package org.fasttrackit;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.fasttrackit.dataprovider.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CheckoutPageTest extends TestConfig {
    Page demoShopPage;
    CartPage cartPage;
    Header header;
    Footer footer;
    CheckoutPage checkoutPage;
    SummaryOrderPage summaryPage;
    Modal modal;

    @BeforeTest
    public void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        cartPage = new CartPage();
        header = new Header();
        footer = new Footer();
        checkoutPage = new CheckoutPage();
        summaryPage = new SummaryOrderPage();
        modal = new Modal();

    }

    @AfterMethod
    public void cleanUp() {
        footer.reset();
        header.homePage();
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest",testName = "Fill in information test",
            description = "Fill in information test")
    @Severity(SeverityLevel.CRITICAL)
    public void whenClickingOnTheCheckoutButtonSummaryPageMustBeDisplayed(Product product) {
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        checkoutPage.clickFirstNameField();
        checkoutPage.typeOnTheFirstNameField("dino");
        checkoutPage.clickLastName();
        checkoutPage.typeOnTheLastNameField("turtle");
        checkoutPage.clickAdressField();
        checkoutPage.typeOnTheAdressField("choochoo23");
        checkoutPage.continueCheckout();
        assertEquals(summaryPage.summaryPageTitle(), "Order summary", "When click continue checkout summary page must be displayed");

    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest",testName = "Checkout only with first name filled",
            description = "Checkout only with first name filled")
    public void whenClickContinueCheckoutWithoutCompleteLastNameAndAdressAnErrorMustBeDisplayed(Product product) {
        new Product(product.getProductId() , product.getName() , product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        checkoutPage.clickFirstNameField();
        checkoutPage.typeOnTheFirstNameField("dino");
        checkoutPage.continueCheckout();
        assertTrue(checkoutPage.completeInformationIsDisplayed(),
                "When click on the continue error message is displayed");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest",testName = "Checkout with first and last name",
            description = "Checkout with first and last name")
    public void whenClickOnContinueWithoutAdressFieldCompleteAnErrorMessageMustBeDisplayed(Product product) {
        new Product(product.getProductId() , product.getName() , product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        checkoutPage.clickFirstNameField();
        checkoutPage.typeOnTheFirstNameField("dino");
        checkoutPage.clickLastName();
        checkoutPage.typeOnTheLastNameField("turtle");
        checkoutPage.continueCheckout();
        assertTrue(checkoutPage.completeInformationIsDisplayed(),
                "When click on the continue error message is displayed");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest",testName = "Checkout with space instead of character",
            description = "Checkout with space instead of character")
    public void completeOrderInsertingSpacesInsteadCharacthers(Product product) {
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        checkoutPage.clickFirstNameField();
        checkoutPage.typeOnTheFirstNameField("dino");
        checkoutPage.clickLastName();
        checkoutPage.typeOnTheLastNameField("turtle");
        checkoutPage.clickAdressField();
        checkoutPage.typeOnTheAdressField("choochoo23");
        checkoutPage.continueCheckout();
        assertTrue(checkoutPage.completeInformationIsDisplayed(),
                "When no word are inserted but space and click continue checkout error message must be displayed");

    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "UserDataProviderTest",testName = "Checkout with space instead of character",
            description = "Checkout with space instead of character")
    public void completeOrderInsertingSpacesInsteadCharacthersWithDifferentUserLogedIn(User user) {
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        Product product = new Product("4", "Practical Wooden Bacon","29.99");
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        checkoutPage.clickFirstNameField();
        checkoutPage.typeOnTheFirstNameField("dino");
        checkoutPage.clickLastName();
        checkoutPage.typeOnTheLastNameField("turtle");
        checkoutPage.clickAdressField();
        checkoutPage.typeOnTheAdressField("choochoo23");
        checkoutPage.continueCheckout();
        assertTrue(checkoutPage.completeInformationIsDisplayed(),
                "When no word are inserted but space and click continue checkout error message must be displayed");

    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenClickOnTheCancelButtonCartPageMustBeDisplayed(Product product) {
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        checkoutPage.cancelOrder();
        assertEquals(cartPage.getCartTitle(), "Your cart");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void verifyThankYouMessageIsDisplayed(Product product) {
        new Product("4", "Practical Wooden Bacon","29.99");
        product.addToCart();
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        checkoutPage.clickFirstNameField();
        checkoutPage.typeOnTheFirstNameField("dino");
        checkoutPage.clickLastName();
        checkoutPage.typeOnTheLastNameField("turtle");
        checkoutPage.clickAdressField();
        checkoutPage.typeOnTheAdressField("choochoo");
        checkoutPage.continueCheckout();
        summaryPage.completeOrder();
        assertTrue(summaryPage.validateThankyouyMessage(), "Expected thank you message to be displayed.");

    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenCancelOrderButoonIsClickedCartPageMustBeDisplayed(Product product){
        new Product("4", "Practical Wooden Bacon","29.99");
        product.addToCart();
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        checkoutPage.clickFirstNameField();
        checkoutPage.typeOnTheFirstNameField("dino");
        checkoutPage.clickLastName();
        checkoutPage.typeOnTheLastNameField("turtle");
        checkoutPage.clickAdressField();
        checkoutPage.typeOnTheAdressField("choochoo");
        checkoutPage.continueCheckout();
        summaryPage.cancelOrder();
        assertEquals(cartPage.getCartTitle(),"Your cart");
    }
}