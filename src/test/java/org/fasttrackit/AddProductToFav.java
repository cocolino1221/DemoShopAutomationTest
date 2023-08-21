package org.fasttrackit;

import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.*;

public class AddProductToFav extends TestConfig {
    Page demoShopPage;
    Header header;
    Footer footer;

    @BeforeTest
    public void setup(){
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
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenAddingAProductToFavBadgeIsIncremented(Product product){
        Product p = new Product(product.getProductId(), product.getName(), product.getPrice());
        p.addToFav();
        assertEquals(header.getFavCounter(),"1","When adding a product to favorite,fav badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenClickingOnAddToFavButtonAgainFavBadgeDisappear(Product product){
        Product p = new Product(product.getProductId(), product.getName(), product.getPrice());
        p.addToFav();
        p.deleteFavProduct();
        assertFalse(header.isFavBadgeDisplayed(),"When clicking on favorite button again, favorite badge must disappear");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenAddingTwoDifferentProductToFavBadgeIsIncrementedBy2(Product product){
        Product p = new Product("Awesome Granite Chips", "1","15.99");
        p.addToFav();
        Product p1 = new Product("Licensed Steel Gloves", "8","14.99");
        p1.addToFav();
        assertEquals(header.getFavCounter(),"2","When adding a product to favorite,fav badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenDeleteOneProductFromMultipleProductFavoriteListBadgeMustDecrement(Product product){
        Product p = new Product("Awesome Granite Chips", "1","15.99");
        Product p1 = new Product("Licensed Steel Gloves", "8","14.99");
        p.addToFav();
        p1.addToFav();
        assertEquals(header.getFavCounter(),"2");
        p1.deleteFavProduct();
        assertEquals(header.getFavCounter(),"1","When delete just one product from favorite badge must decrement by 1");
    }
}
