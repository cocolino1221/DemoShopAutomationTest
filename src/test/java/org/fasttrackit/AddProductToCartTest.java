package org.fasttrackit;

import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddProductToCartTest extends TestConfig {
    Page demoShopPage;
    Header header;
    Footer footer;

    @BeforeTest
    private void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        header = new Header();
        footer = new Footer();
    }
    @AfterMethod
    public void cleanUp() {
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
    @Test
    public void whenAddingTwoIdenticalToCart_CartBadgeIsIncrementedBy2() {
        Product p = new Product("1", "Awesome Granite Chips","15.99");
        p.addToCart();
        p.addToCart();
        Assert.assertEquals(header.getCartCounter(), "2",
                "When adding a product to cart, cart badge must increment by 1");
    }

    @Test
    public void whenAddingTwoDifferentProductToCart_CartBadgeIsIncrementedBy2() {
        Product p1 = new Product("1", "Awesome Granite Chips","15.99");
        Product p2 = new Product("4", "Practical Wooden Bacon","29.99");
        p1.addToCart();
        p2.addToCart();
        Assert.assertEquals(header.getCartCounter(), "2", "When adding a product to cart, cart badge must increment by 1");
    }


}
