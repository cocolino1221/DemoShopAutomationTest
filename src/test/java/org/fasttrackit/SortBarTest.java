package org.fasttrackit;

import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;


public class SortBarTest extends TestConfig {
    Page demoShopPage;
    Header header;
    Footer footer;
    CartPage cart;
    SortBarPage sortBarPage;

    @BeforeTest
    public void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        header = new Header();
        footer = new Footer();
        cart = new CartPage();
        demoShopPage.searchBarClick();
        sortBarPage = new SortBarPage();
    }
    @AfterMethod
    public void cleanUp(){
        footer.reset();
        header.homePage();
    }

    @Test
    public void whenClickOnTheAToZSortProductWithAMustBeDisplayedFirst(){
        demoShopPage.openPage();
        sortBarPage.sortBarClick();
        sortBarPage.clickAToZSort();
        assertEquals(demoShopPage.getFirstProduct(),demoShopPage.getLastProduct());

    }
    @Test
    public void  whenClickOnTheZToASortProductWithZMustBeDisplayedFirst(){
        sortBarPage.sortBarClick();
        sortBarPage.clickZToASort();
        assertEquals(demoShopPage.getLastProduct(),demoShopPage.getFirstProduct());
    }
    @Test
    public void whenClickOnTheLowToHighSortProductWithLowestPriceMustBeDisplayedFirst() {
        sortBarPage.sortBarClick();
        sortBarPage.clickLowToHighSort();
    }
    @Test
    public void whenClickOnTheHighToLowSortProductWithHigherPriceMustBeDisplayedFirst(){
        sortBarPage.sortBarClick();
        sortBarPage.clickHighToLowSort();
    }
}
