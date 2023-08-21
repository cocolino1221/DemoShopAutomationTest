package org.fasttrackit;

import com.codeborne.selenide.Selenide;
import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.fasttrackit.dataprovider.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class SortBarTest extends TestConfig {
    Page demoShopPage;
    Header header;
    Footer footer;
    CartPage cart;
    SortBarPage sortBarPage;
    Modal modal;

    @BeforeTest
    public void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        header = new Header();
        footer = new Footer();
        cart = new CartPage();
        demoShopPage.searchBarClick();
        sortBarPage = new SortBarPage();
        modal = new Modal();
    }
    @AfterMethod
    public void cleanUp(){
        footer.reset();
        header.homePage();
        Selenide.refresh();
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "UserDataProviderTest",description = "Test sort A to Z with different users")
    public void whenClickOnTheAToZSortProductWithAMustBeDisplayedFirst(User user){
        demoShopPage.openPage();
        header.clickOnTheLoginIcon();
        modal.typeInUsername(user.getUserName());
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        sortBarPage.sortBarClick();
        sortBarPage.clickAToZSort();
        assertFalse(demoShopPage.firstProductDisplayAToZ(),"Awesome granite chips must displayed first");

    }
    @Test
    public void  whenClickOnTheZToASortProductWithZMustBeDisplayedFirst(){
        sortBarPage.sortBarClick();
        sortBarPage.clickZToASort();
        assertFalse(demoShopPage.lastProductDisplayFirstZToA());
    }
    @Test
    public void whenClickOnTheLowToHighSortProductWithLowestPriceMustBeDisplayedFirst() {
        sortBarPage.sortBarClick();
        sortBarPage.clickLowToHighSort();
        assertFalse(demoShopPage.lowestPriceFirst(),
                "When choose low to high sort, lowest product price must be displayed first");
    }
    @Test
    public void whenClickOnTheHighToLowSortProductWithHigherPriceMustBeDisplayedFirst(){
        sortBarPage.sortBarClick();
        sortBarPage.clickHighToLowSort();
        assertFalse(demoShopPage.highestPriceFirst(),
                "When choose high to low sort, highest price must be displayed first");
    }
}
