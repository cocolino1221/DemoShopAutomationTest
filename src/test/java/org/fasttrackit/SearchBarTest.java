package org.fasttrackit;

import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.*;

public class SearchBarTest extends TestConfig {
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
    }

    @Test
    public void whenClickOnSearchBar(){
        demoShopPage.searchBarClick();
        assertFalse(demoShopPage.searchBarIsEnabled(),"When clicking on the searchbar you must be able to input text");
    }
    @Test
    public void whenSearchForAWordAllContainingProductsMustBeDisplayed(){
        demoShopPage.searchBarClick();
        demoShopPage.typeOnTheSearchBar("awesome");
        demoShopPage.searchButtonClick();
    }
}
