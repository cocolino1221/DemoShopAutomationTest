package org.fasttrackit;

import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class CartPageTest extends TestConfig {
    Page demoShopPage;
    CartPage cartPage;
    Header header;
    Footer footer;
    CheckoutPage checkoutPage;

    @BeforeTest
    public void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        cartPage = new CartPage();
        header = new Header();
        footer = new Footer();
        checkoutPage = new CheckoutPage();
    }

    @AfterMethod
    public void cleanUp() {
        header.homePage();
        footer.reset();
        cartPage = new CartPage();
    }

    @Test()
    public void whenCartPageIsEmptyGreetingsMessageIsDisplayed() {
        header.clickOnTheCartIcon();
        assertEquals(cartPage.getGreetingsMessage(), "How about adding some products in your cart?",
                "When empty cart page is open greetings message ,ust be displayed");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void verifyTheCartAfterAddingAProductToCart(Product product) {
        product.addToCart();
        header.clickOnTheCartIcon();
        assertEquals(header.getCartCounter(), "1", "When oppening cart, one product must appear on cart page");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void deleteProductFromCart(Product product) {
        product.addToCart();
        assertEquals(header.getCartCounter(), "1");
        header.clickOnTheCartIcon();
        cartPage.withProduct(product);
        cartPage.getProductsInCarts().get(0).deleteFromCart();
        assertEquals(cartPage.getGreetingsMessage(), "How about adding some products in your cart?",
                "When empty cart page is open greetings message ,ust be displayed");
    }
    @Test()
    public void whenDeleteOneProductFromCartWithMultipleProductsItMustDeleteProduct(){
        Product p = new Product("1","Awesome Granite Chips","15.99");
        Product p1 = new Product("5","Awesome Soft Shirt","29.99");
        Product p2 = new Product("9","Gorgeous Soft Pizza", "19.99");
        p.addToCart();
        p1.addToCart();
        p2.addToCart();
        assertEquals(header.getCartCounter(),"3");
        header.clickOnTheCartIcon();
        cartPage.withProduct(p);
        cartPage.withProduct(p1);
        cartPage.withProduct(p2);
        cartPage.getProductsInCarts().get(1).deleteFromCart();
        assertEquals(header.getCartCounter(),"2","When delete a product from cart product must be deleted");
    }

    @Test()
    public void increaseAmountByClickOnPlusButton() {
        Product p1 = new Product("1","Awesome Granite Chips","15.99");
        Product p2 = new Product("5","Awesome Soft Shirt","29.99");
        p1.addToCart();
        p2.addToCart();
        assertEquals(header.getCartCounter(), "2");
        header.clickOnTheCartIcon();
        cartPage.withProduct(p1);
        cartPage.withProduct(p2);
        cartPage.getProductsInCarts().get(0).increaseAmount();
        assertEquals(header.getCartCounter(), "3");
        assertEquals(cartPage.getProductsCount(), "2");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void decreaseAmountByClickingOnMinusButton(Product product) {
        product.addToCart();
        product.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "3");
        header.clickOnTheCartIcon();
        cartPage.withProduct(product);
        cartPage.getProductsInCarts().get(0).reduceAmount();
        assertEquals(header.getCartCounter(),"2");
        assertEquals(cartPage.getProductsCount(), "1");
        assertEquals(cartPage.getProductsInCarts().get(0).getCount(),"2");
    }

    @Test()
    public void whenAddingMultipleProductToCartCounterMustShowCorrectNumber() {
        Product p = new Product("1","Awesome Granite Chips","15.99");
        Product p1 = new Product("5","Awesome Soft Shirt","29.99");
        Product p2 = new Product("9","Gorgeous Soft Pizza", "19.99");
        p.addToCart();
        p.addToCart();
        p1.addToCart();
        p2.addToCart();
        assertEquals(header.getCartCounter(), "4");
        header.clickOnTheCartIcon();
        cartPage.withProduct(p);
        cartPage.withProduct(p);
        cartPage.withProduct(p1);
        cartPage.withProduct(p2);
        assertEquals(cartPage.getProductsCount(),"3");

    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void clickOnTheContinueShoppingButtonToGoBackToShoppingPage(Product product){
        product.addToCart();
        assertEquals(header.getCartCounter(),"1");
        header.clickOnTheCartIcon();
        cartPage.continueShoppingButton();
        assertEquals(demoShopPage.getTitle(),"Demo shop",
                "When clicking on the continue shopping button shopping page must appear!");

    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void whenClickOnCheckoutButton_CheckoutPageMustOpen(Product product){
        product.addToCart();
        assertEquals(header.getCartCounter(),"1");
        header.clickOnTheCartIcon();
        cartPage.checkoutButton();
        assertEquals(checkoutPage.getCheckoutPageTitle(),"Your information");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void checkIfTheTotalAmountIsCorectlyCalculated(Product product) {
        product.addToCart();
        product.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "3");
        header.clickOnTheCartIcon();
        cartPage.withProduct(product);
        cartPage.withProduct(product);
        cartPage.withProduct(product);
        ProductInCart productInCart = new ProductInCart(product);
        assertEquals(cartPage.getProductsCount(), "1");
        String firstArticlePrice = productInCart.getTotalProductPrice();
        Double firstPrice = Double.valueOf(firstArticlePrice.replaceAll("\\$",""));
        assertEquals(productInCart.getTotalProductPrice(),cartPage.totalPrice(),"");


    }
    @Test
    public void checkIfTotalAmountIsCorectlyCalculatedWithDifferentProducts(){
        Product product1 = new Product("8","Licensed Steel Gloves","14.99");
        Product product2 = new Product("6","Practical Wooden Bacon", "1.99");
        product1.addToCart();
        product2.addToCart();
        assertEquals(header.getCartCounter(), "2");
        header.clickOnTheCartIcon();
        cartPage.withProduct(product1);
        cartPage.withProduct(product2);
        assertEquals(cartPage.getProductsCount(),"2",
                "When adding 2 different product to cart must be two different rows");
        ProductInCart productInCart = new ProductInCart(product1);
        ProductInCart secondProductInCart = new ProductInCart(product2);
        String firstArticlePrice = productInCart.getTotalProductPrice();
        String secondArticlePrice = secondProductInCart.getTotalProductPrice();
        Double firstPrice = Double.valueOf(firstArticlePrice.replaceAll("\\$",""));
        Double secondPrice = Double.valueOf(secondArticlePrice.replaceAll("\\$",""));
        double total = firstPrice + secondPrice;
        assertEquals( cartPage.totalPrice(), total,
                "When adding two different product to cart the total ammount and sum of products prices must be equal");


    }






}
