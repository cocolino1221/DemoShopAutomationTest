package org.fasttrackit;

import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
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


    @BeforeTest
    public void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        cartPage = new CartPage();
        header = new Header();
        footer = new Footer();
        checkoutPage = new CheckoutPage();
        sortBarPage = new SortBarPage();


    }

    @AfterMethod
    public void cleanUp() {
        footer.reset();
        header.homePage();
    }
    //SearchBar
    @Test
    public void validateSearchBarAppear(){
        assertFalse(demoShopPage.setSearchBar(),"When opne the homepage searchbar must appear");
    }
    //SortBar function
    @Test
    public void validateTheSortBarExistAndisClickable(){
        assertFalse(demoShopPage.sortBarIsDisplayed(),"When open the homepage sortbar must be displayed");
        assertTrue(demoShopPage.sortBarIsEnabled(),"When clicking on the sortbar sort option must appear");
    }
    @Test
    public void validateThatSortAToZIsDisplayed (){
        assertTrue(sortBarPage.validateSortAToZIsEnabledAndDisplayed(),"When click on the sort bar sort A to Z must be displayed");
    }
    @Test
    public void validateThatSortZToAIsDisplayed (){
        assertTrue(sortBarPage.validateSortZToAIsEnabledAndDisplayed(),"When click on the sort bar sort Z to A must be displayed");
    }
    @Test
    public void validateLowToHighSortIsDisplayed(){
        assertTrue(sortBarPage.validateSortByPriceLowToHighIsEnabledAndDisplayed(),"When click on the sort bar sort Low to High must be displayed");
    }
    @Test
    public void validateHighToLowSortIsDisplayed() {
        assertTrue(sortBarPage.validateSortByPriceHighToLowIsEnabledAndDisplayed(),"When click on the sort bar sort High to Low must be displayed");
    }
    //Checkout Page function
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateLastNameIsDisplayedAndEnabled(Product product){
        Product p1 = new Product(product.getProductId(), product.getName(), product.getPrice());
        p1.addToCart();
        assertEquals(header.getCartCounter(),"1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(),"Your information");
        assertTrue(checkoutPage.validateLastNameisDisplayedAndEnabled(),"Last name field must be displayed and enabled");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "ProductsDetailsDataProviderTest")
    public void validateFirstNameIsDisplayedAndEnabled(Product product){
        Product p1 = new Product(product.getProductId(), product.getName(), product.getPrice());
        p1.addToCart();
        assertEquals(header.getCartCounter(),"1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(),"Your information");
        assertTrue(checkoutPage.validateFirstNameIsDisplayedAndEnabled(),"FIrst name field must be dipalyed and enabled");
    }


}
