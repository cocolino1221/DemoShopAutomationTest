package org.fasttrackit;

import com.codeborne.selenide.Selenide;
import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.fasttrackit.dataprovider.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AddProductToFavTest extends TestConfig {
    Page demoShopPage;
    Header header;
    Footer footer;
    Modal modal;


    @BeforeTest
    public void setup(){
        demoShopPage = new Page();
        demoShopPage.openPage();
        header = new Header();
        footer = new Footer();
        modal = new Modal();
    }
    @AfterMethod
    public void cleanUp(){
        Selenide.refresh();
        footer.reset();
        header.homePage();
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class ,dataProvider = "ProductsDetailsDataProviderTest")
    public void whenAddingAProductToFavBadgeIsIncrementedWithDinoUser(Product product){
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername("dino");
        modal.clickOnThePasswordField();
        modal.typeInPassword("choochoo");
        modal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),"Hi dino!");
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
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest1")
    public void whenAddingTwoDifferentProductToFavBadgeIsIncrementedBy2(Product product){
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToFav();
        Product p1 = new Product("1","Awesome Granite Chips","15.99");
        p1.addToFav();
        assertEquals(header.getFavCounter(),"2","When adding a product to favorite,fav badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest1")
    public void whenAddingTwoDifferentProductToFavLoggedInWithDinoBadgeIsIncrementedBy2(Product product){
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername("dino");
        modal.clickOnThePasswordField();
        modal.typeInPassword("choochoo");
        modal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),"Hi dino!");
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToFav();
        Product p1 = new Product("1","Awesome Granite Chips","15.99");
        p1.addToFav();
        assertEquals(header.getFavCounter(),"2","When adding a product to favorite,fav badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest1")
    public void whenAddingTwoDifferentProductToFavLoggedInWithTurtleBadgeIsIncrementedBy2(Product product){
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername("turtle");
        modal.clickOnThePasswordField();
        modal.typeInPassword("choochoo");
        modal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),"Hi turtle!");
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToFav();
        Product p1 = new Product("1","Awesome Granite Chips","15.99");
        p1.addToFav();
        assertEquals(header.getFavCounter(),"2","When adding a product to favorite,fav badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest1")
    public void whenAddingTwoDifferentProductToFavLoggedInWithBeetleBadgeIsIncrementedBy2(Product product){
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername("beetle");
        modal.clickOnThePasswordField();
        modal.typeInPassword("choochoo");
        modal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),"Hi beetle!");
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToFav();
        Product p1 = new Product("1","Awesome Granite Chips","15.99");
        p1.addToFav();
        assertEquals(header.getFavCounter(),"2","When adding a product to favorite,fav badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest1")
    public void whenDeleteOneProductFromMultipleProductFavoriteListBadgeMustDecrement(Product product){
        new Product(product.getProductId(), product.getName(), product.getPrice());
        Product p1 = new Product("1","Awesome Granite Chips","15.99");
        product.addToFav();
        p1.addToFav();
        assertEquals(header.getFavCounter(),"2");
        p1.deleteFavProduct();
        assertEquals(header.getFavCounter(),"1","When delete just one product from favorite badge must decrement by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest1")
    public void whenLoggedInWithDinoAndDeleteOneProductFromMultipleProductFavoriteListBadgeMustDecrement(Product product){
        new Product(product.getProductId(), product.getName(), product.getPrice());
        Product p1 = new Product("1","Awesome Granite Chips","15.99");
        product.addToFav();
        p1.addToFav();
        assertEquals(header.getFavCounter(),"2");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername("dino");
        modal.clickOnThePasswordField();
        modal.typeInPassword("choochoo");
        modal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),"Hi dino!");
        p1.deleteFavProduct();
        assertEquals(header.getFavCounter(),"1","When delete just one product from favorite badge must decrement by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest1")
    public void whenLoggedInWithTurtleAndDeleteOneProductFromMultipleProductFavoriteListBadgeMustDecrement(Product product){
        new Product(product.getProductId(), product.getName(), product.getPrice());
        Product p1 = new Product("1","Awesome Granite Chips","15.99");
        product.addToFav();
        p1.addToFav();
        assertEquals(header.getFavCounter(),"2");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername("turtle");
        modal.clickOnThePasswordField();
        modal.typeInPassword("choochoo");
        modal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),"Hi turtle!");
        p1.deleteFavProduct();
        assertEquals(header.getFavCounter(),"1","When delete just one product from favorite badge must decrement by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest1")
    public void whenLoggedInWithBeetleAndDeleteOneProductFromMultipleProductFavoriteListBadgeMustDecrement(Product product){
        new Product(product.getProductId(), product.getName(), product.getPrice());
        Product p1 = new Product("1","Awesome Granite Chips","15.99");
        product.addToFav();
        p1.addToFav();
        assertEquals(header.getFavCounter(),"2");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername("beetle");
        modal.clickOnThePasswordField();
        modal.typeInPassword("choochoo");
        modal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),"Hi beetle!");
        p1.deleteFavProduct();
        assertEquals(header.getFavCounter(),"1","When delete just one product from favorite badge must decrement by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest1")
    public void whenLoggedInAndDeleteOneProductFromMultipleProductFavoriteListBadgeMustDecrement(Product product){
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername("dino");
        modal.clickOnThePasswordField();
        modal.typeInPassword("choochoo");
        modal.clickOnTheLoginButtonFromModal();
        assertEquals(header.getGreetingsMessage(),"Hi dino!");
        new Product(product.getProductId(), product.getName(), product.getPrice());
        Product p1 = new Product("1","Awesome Granite Chips","15.99");
        product.addToFav();
        p1.addToFav();
        assertEquals(header.getFavCounter(),"2");
        p1.deleteFavProduct();
        assertEquals(header.getFavCounter(),"1","When delete just one product from favorite badge must decrement by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class ,dataProvider = "ProductsDetailsDataProviderTest")
    public void whenAddingAProductToFavBadgeIsIncremented(Product product){
        Product p = new Product(product.getProductId(), product.getName(), product.getPrice());
        p.addToFav();
        assertEquals(header.getFavCounter(),"1","When adding a product to favorite,fav badge must increment by 1");
    }
}
