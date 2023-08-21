package org.fasttrackit;

import com.codeborne.selenide.Selenide;
import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.fasttrackit.dataprovider.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertFalse;

public class ValidationTest extends TestConfig {
    Page demoShopPage;
    CartPage cartPage;
    Header header;
    Footer footer;
    CheckoutPage checkoutPage;
    SortBarPage sortBarPage;
    Modal modal;


    @BeforeTest
    public void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        cartPage = new CartPage();
        header = new Header();
        footer = new Footer();
        checkoutPage = new CheckoutPage();
        sortBarPage = new SortBarPage();
        modal = new Modal();


    }

    @AfterMethod
    public void cleanUp() {
        Selenide.refresh();
        footer.reset();
        header.homePage();
    }

    //SearchBar
    @Test
    public void validateSearchBarAppear() {
        assertFalse(demoShopPage.setSearchBar(), "When opne the homepage searchbar must appear");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "UserDataProviderTest", description = "Verify that searchbar appear when logged in")
    public void validateSearchBarAppearWhenLoggedIn(User user) {
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertFalse(demoShopPage.setSearchBar(), "When open the homepage and log in searchbar must appear");
    }

    //SortBar function
    @Test
    public void validateTheSortBarExistAndisClickable() {
        assertFalse(demoShopPage.sortBarIsDisplayed(), "When open the homepage sortbar must be displayed");
        assertTrue(demoShopPage.sortBarIsEnabled(), "When clicking on the sortbar sort option must appear");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "UserDataProviderTest", description = "Check if sort bar is dispalyed and clickable after log in")
    public void validateTheSortBarExistAndIsClickableWhenLoggedIn(User user) {
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertFalse(demoShopPage.sortBarIsDisplayed(), "When open the homepage sortbar must be displayed");
        assertTrue(demoShopPage.sortBarIsEnabled(), "When clicking on the sortbar sort option must appear");
    }

    @Test
    public void validateThatSortAToZIsDisplayed() {
        assertTrue(sortBarPage.validateSortAToZIsEnabledAndDisplayed(), "When click on the sort bar sort A to Z must be displayed");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "UserDataProviderTest", description = "Check if sort A to Z is dispalyed and clickable after log in")
    public void validateThatSortAToZIsDisplayedWhenLoggedin(User user) {
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertTrue(sortBarPage.validateSortAToZIsEnabledAndDisplayed(), "When click on the sort bar sort A to Z must be displayed");
    }

    @Test
    public void validateThatSortZToAIsDisplayed() {
        assertTrue(sortBarPage.validateSortZToAIsEnabledAndDisplayed(), "When click on the sort bar sort Z to A must be displayed");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "UserDataProviderTest", description = "Check if sort Z to A is dispalyed and clickable after log in")
    public void validateThatSortZToAIsDisplayedWhenLoggedin(User user) {
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertTrue(sortBarPage.validateSortZToAIsEnabledAndDisplayed(), "When click on the sort bar sort Z to A must be displayed");
    }

    @Test
    public void validateLowToHighSortIsDisplayed() {
        assertTrue(sortBarPage.validateSortByPriceLowToHighIsEnabledAndDisplayed(), "When click on the sort bar sort Low to High must be displayed");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "UserDataProviderTest", description = "Check if sort Low to High is dispalyed and clickable after log in")
    public void validateLowToHighSortIsDisplayedWhenLoggedin(User user) {
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertTrue(sortBarPage.validateSortByPriceLowToHighIsEnabledAndDisplayed(), "When click on the sort bar sort Low to High must be displayed");
    }

    @Test
    public void validateHighToLowSortIsDisplayed() {
        assertTrue(sortBarPage.validateSortByPriceHighToLowIsEnabledAndDisplayed(), "When click on the sort bar sort High to Low must be displayed");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "UserDataProviderTest", description = "Check if sort High to Low is dispalyed and clickable after log in")
    public void validateHighToLowSortIsDisplayedWhenLoggedin(User user) {
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertTrue(sortBarPage.validateSortByPriceHighToLowIsEnabledAndDisplayed(), "When click on the sort bar sort High to Low must be displayed");
    }

    //Checkout Page function
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateLastNameIsDisplayedAndEnabled(Product product) {
        Product p1 = new Product(product.getProductId(), product.getName(), product.getPrice());
        p1.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        assertTrue(checkoutPage.validateLastNameisDisplayedAndEnabled(), "Last name field must be displayed and enabled");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateLastNameIsDisplayedAndEnabledWhenLoggedInWithDinoUser(Product product) {
        User user = new User("dino","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        assertTrue(checkoutPage.validateLastNameisDisplayedAndEnabled(), "Last name field must be displayed and enabled when logged in");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateLastNameIsDisplayedAndEnabledWhenLoggedInWithTurtleUser(Product product) {
        User user = new User("turtle","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        assertTrue(checkoutPage.validateLastNameisDisplayedAndEnabled(), "Last name field must be displayed and enabled when logged in");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateLastNameIsDisplayedAndEnabledWhenLoggedInWithBeetleUser(Product product) {
        User user = new User("beetle","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        assertTrue(checkoutPage.validateLastNameisDisplayedAndEnabled(), "Last name field must be displayed and enabled when logged in");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateFirstNameIsDisplayedAndEnabled(Product product) {
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        assertTrue(checkoutPage.validateFirstNameIsDisplayedAndEnabled(), "FIrst name field must be dipalyed and enabled");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateFirstNameIsDisplayedAndEnabledWhenLoggedInWithDinoUser(Product product) {
        User user = new User("dino", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        assertTrue(checkoutPage.validateFirstNameIsDisplayedAndEnabled(), "FIrst name field must be dipalyed and enabled");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateFirstNameIsDisplayedAndEnabledWhenLoggedInWithTurtleUser(Product product) {
        User user = new User("turtle", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        assertTrue(checkoutPage.validateFirstNameIsDisplayedAndEnabled(), "FIrst name field must be dipalyed and enabled");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateFirstNameIsDisplayedAndEnabledWhenLoggedInWithBeetleUser(Product product) {
        User user = new User("beetle", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(), "Your information");
        assertTrue(checkoutPage.validateFirstNameIsDisplayedAndEnabled(), "FIrst name field must be dipalyed and enabled");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void productNameIsDisplayed(Product product) {
        assertFalse(product.validateProductTitleExist(), "Expected product name to be displayed.");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void productNameIsDisplayedWhenLoggedInWithDinoUser(Product product) {
        User user = new User("dino", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertFalse(product.validateProductTitleExist(), "Expected product name to be displayed.");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void productNameIsDisplayedWhenLoggedInWithTurtleUser(Product product) {
        User user = new User("turtle", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertFalse(product.validateProductTitleExist(), "Expected product name to be displayed.");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void productNameIsDisplayedWhenLoggedInWithBeetleUser(Product product) {
        User user = new User("beetle", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertFalse(product.validateProductTitleExist(), "Expected product name to be displayed.");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateThatProductImageIsDisplayed(Product product) {
        assertTrue(product.validatePictureIsDisplayed(), "Product picture must be displayed");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateThatProductImageIsDisplayedAfterComeBackFromCartPage(Product product) {
        header.clickOnTheCartIcon();
        Selenide.back();
        assertTrue(product.validatePictureIsDisplayed(), "Product picture must be displayed");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateThatProductImageIsDisplayedAfterComeBackFromCartPageAndLoggedInWithDinoUser(Product product) {
        User user = new User("dino", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        header.clickOnTheCartIcon();
        Selenide.back();
        assertTrue(product.validatePictureIsDisplayed(), "Product picture must be displayed");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateThatProductImageIsDisplayedAfterComeBackFromCartPageAndLoggedInWithTurtleUser(Product product) {
        User user = new User("turtle", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        header.clickOnTheCartIcon();
        Selenide.back();
        assertTrue(product.validatePictureIsDisplayed(), "Product picture must be displayed");
    }@Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateThatProductImageIsDisplayedAfterComeBackFromCartPageAndLoggedInWithBeetleUser(Product product) {
        User user = new User("beetle", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        header.clickOnTheCartIcon();
        Selenide.back();
        assertTrue(product.validatePictureIsDisplayed(), "Product picture must be displayed");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateThatProductImageIsDisplayedWithDinoUserLoggedIn(Product product) {
        User user = new User("dino", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertTrue(product.validatePictureIsDisplayed(), "Product picture must be displayed");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateThatProductImageIsDisplayedWithTurtleUserLoggedIn(Product product) {
        User user = new User("turtle", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertTrue(product.validatePictureIsDisplayed(), "Product picture must be displayed");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateThatProductImageIsDisplayedWithBeetleUserLoggedIn(Product product) {
        User user = new User("beetle", "choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        assertTrue(product.validatePictureIsDisplayed(), "Product picture must be displayed");
    }


}
