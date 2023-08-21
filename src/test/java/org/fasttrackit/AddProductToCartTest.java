package org.fasttrackit;

import com.codeborne.selenide.Selenide;
import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.fasttrackit.dataprovider.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddProductToCartTest extends TestConfig {
    Page demoShopPage;
    Header header;
    Footer footer;
    Modal loginModal;

    @BeforeTest
    private void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        header = new Header();
        footer = new Footer();
        loginModal = new Modal();

    }
    @AfterMethod
    public void cleanUp() {
        Selenide.refresh();
        footer.reset();
        header.homePage();
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenAddingAProductToCart_CartBadgeIsIncremented(Product product) {
        new Product(product.getProductId(), product.getName(), product.getPrice());
        product.addToCart();
        Assert.assertEquals(header.getCartCounter(), "1",
                "When adding a product to cart, cart badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenAddingTwoIdenticalToCart_CartBadgeIsIncrementedBy2(Product product) {
        Product p = new Product(product.getProductId(),product.getName(),product.getPrice());
        p.addToCart();
        p.addToCart();
        Assert.assertEquals(header.getCartCounter(), "2",
                "When adding a product to cart, cart badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenLoggedInWithDinoAndAddingTwoIdenticalToCart_CartBadgeIsIncrementedBy2(Product product) {
        User user = new User("dino", "choochoo");
        header.clickOnTheLoginIcon();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        Product p = new Product(product.getProductId(),product.getName(),product.getPrice());
        p.addToCart();
        p.addToCart();
        Assert.assertEquals(header.getCartCounter(), "2",
                "When adding a product to cart, cart badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenLoggedInWithBeetleAndAddingTwoIdenticalToCart_CartBadgeIsIncrementedBy2(Product product) {
        User user = new User("beetle", "choochoo");
        header.clickOnTheLoginIcon();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        Product p = new Product(product.getProductId(),product.getName(),product.getPrice());
        p.addToCart();
        p.addToCart();
        Assert.assertEquals(header.getCartCounter(), "2",
                "When adding a product to cart, cart badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenLoggedInWithTurtleAndAddingTwoIdenticalToCart_CartBadgeIsIncrementedBy2(Product product) {
        User user = new User("turtle", "choochoo");
        header.clickOnTheLoginIcon();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        Product p = new Product(product.getProductId(),product.getName(),product.getPrice());
        p.addToCart();
        p.addToCart();
        Assert.assertEquals(header.getCartCounter(), "2",
                "When adding a product to cart, cart badge must increment by 1");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest1")
    public void whenAddingTwoDifferentProductToCart_CartBadgeIsIncrementedBy2(Product product) {
        Product p1 = new Product("1", "Awesome Granite Chips","15.99");
        product = new Product(product.getProductId(), product.getName(), product.getPrice());
        p1.addToCart();
        product.addToCart();
        Assert.assertEquals(header.getCartCounter(), "2", "When adding a product to cart, cart badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenLoggedInWithDinoAndAddingTwoDifferentProductToCart_CartBadgeIsIncrementedBy2(Product product) {
        User user = new User("dino", "choochoo");
        header.clickOnTheLoginIcon();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        Product p1 = new Product("1", "Awesome Granite Chips","15.99");
        product = new Product(product.getProductId(), product.getName(), product.getPrice());
        p1.addToCart();
        product.addToCart();
        Assert.assertEquals(header.getCartCounter(), "2", "When adding a product to cart, cart badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenLoggedInWithBeetleAndAddingTwoDifferentProductToCart_CartBadgeIsIncrementedBy2(Product product) {
        User user = new User("beetle", "choochoo");
        header.clickOnTheLoginIcon();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        Product p1 = new Product("1", "Awesome Granite Chips","15.99");
        product = new Product(product.getProductId(), product.getName(), product.getPrice());
        p1.addToCart();
        product.addToCart();
        Assert.assertEquals(header.getCartCounter(), "2", "When adding a product to cart, cart badge must increment by 1");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenLoggedInWithTurtleAndAddingTwoDifferentProductToCart_CartBadgeIsIncrementedBy2(Product product) {
        User user = new User("turtle", "choochoo");
        header.clickOnTheLoginIcon();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        Product p1 = new Product("1", "Awesome Granite Chips","15.99");
        product = new Product(product.getProductId(), product.getName(), product.getPrice());
        p1.addToCart();
        product.addToCart();
        Assert.assertEquals(header.getCartCounter(), "2", "When adding a product to cart, cart badge must increment by 1");
    }


}
