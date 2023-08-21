package org.fasttrackit;

import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.fasttrackit.dataprovider.User;
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
    Modal modal;


    @BeforeTest
    public void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        cartPage = new CartPage();
        header = new Header();
        footer = new Footer();
        checkoutPage = new CheckoutPage();
        modal = new Modal();
    }

    @AfterMethod
    public void cleanUp() {
        header.homePage();
        footer.reset();
        cartPage = new CartPage();
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class,dataProvider = "UserDataProviderTest",description ="Check cart with different user" )
    public void whenCartPageIsEmptyGreetingsMessageIsDisplayed(User user) {
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        header.clickOnTheCartIcon();
        assertEquals(cartPage.getGreetingsMessage(), "How about adding some products in your cart?",
                "When empty cart page is open greetings message ,ust be displayed");
    }
    @Test
    public void checkIfMessageGreetingIsDsiplayedWithGuest (){
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

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest2Less")
    public void whenDeleteOneProductFromCartWithMultipleProductsItMustDeleteProduct(Product product){
        Product p = new Product("1","Awesome Granite Chips","15.99");
        Product p1 = new Product("5","Awesome Soft Shirt","29.99");
        new Product(product.getProductId(), product.getName(), product.getPrice());
        p.addToCart();
        p1.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(),"3");
        header.clickOnTheCartIcon();
        cartPage.withProduct(p);
        cartPage.withProduct(p1);
        cartPage.withProduct(product);
        cartPage.getProductsInCarts().get(1).deleteFromCart();
        assertEquals(header.getCartCounter(),"2","When delete a product from cart product must be deleted");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest1")
    public void increaseAmountByClickOnPlusButtonWithMultipleProducts(Product product) {
        Product p1 = new Product("1","Awesome Granite Chips","15.99");
        new Product(product.getProductId(), product.getName(), product.getPrice());
        p1.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "2");
        header.clickOnTheCartIcon();
        cartPage.withProduct(p1);
        cartPage.withProduct(product);
        cartPage.getProductsInCarts().get(0).increaseAmount();
        assertEquals(header.getCartCounter(), "3");
        assertEquals(cartPage.getProductsCount(), "2");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void increaseAmountByClickOnPlusButtonWithOneProduct(Product product) {
        new Product(product.getProductId() , product.getName(), product.getPrice());
        product.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "2");
        header.clickOnTheCartIcon();
        cartPage.withProduct(product);
        cartPage.withProduct(product);
        cartPage.getProductsInCarts().get(0).increaseAmount();
        assertEquals(header.getCartCounter(), "3");
        assertEquals(cartPage.getProductsCount(), "2");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsAndUserDetailsDataProviderTest")
    public void increaseAmountByClickOnPlusButtonWithOneProductLoggedInWithDinoUser(Product product) {
        User user = new User("dino","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        new Product(product.getProductId() , product.getName(), product.getPrice());
        product.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "2");
        header.clickOnTheCartIcon();
        cartPage.withProduct(product);
        cartPage.withProduct(product);
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
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsAndUserDetailsDataProviderTest")
    public void decreaseAmountByClickingOnMinusButtonLoggedWithDinoUser(Product product) {
        User user = new User("dino","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        product.addToCart();
        product.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "3");
        header.clickOnTheCartIcon();
        cartPage.withProduct(product);
        cartPage.getProductsInCarts().get(0).reduceAmount();
        assertEquals(header.getCartCounter(),"2");
        assertEquals(cartPage.getProductsCount(), "1");
        assertEquals(cartPage.getProductsInCarts().get(0).getCount(),"2","When logged in and click on minus button amount must decrease by one");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsAndUserDetailsDataProviderTest")
    public void decreaseAmountByClickingOnMinusButtonLoggedWithTurtleUser(Product product) {
        User user = new User("turtle","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        product.addToCart();
        product.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "3");
        header.clickOnTheCartIcon();
        cartPage.withProduct(product);
        cartPage.getProductsInCarts().get(0).reduceAmount();
        assertEquals(header.getCartCounter(),"2");
        assertEquals(cartPage.getProductsCount(), "1");
        assertEquals(cartPage.getProductsInCarts().get(0).getCount(),"2","When logged in and click on minus button amount must decrease by one");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsAndUserDetailsDataProviderTest")
    public void decreaseAmountByClickingOnMinusButtonLoggedWithBeetleUser(Product product) {
        User user = new User("beetle","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        product.addToCart();
        product.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "3");
        header.clickOnTheCartIcon();
        cartPage.withProduct(product);
        cartPage.getProductsInCarts().get(0).reduceAmount();
        assertEquals(header.getCartCounter(),"2");
        assertEquals(cartPage.getProductsCount(), "1");
        assertEquals(cartPage.getProductsInCarts().get(0).getCount(),"2","When logged in and click on minus button amount must decrease by one");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class,dataProvider = "ProductsDetailsDataProviderTest2Less")
    public void whenAddingMultipleDifferentProductsToCartCounterMustShowCorrectNumber(Product product) {
        Product p = new Product("1","Awesome Granite Chips","15.99");
        Product p1 = new Product("5","Awesome Soft Shirt","29.99");
        new Product(product.getProductId(), product.getName(), product.getPrice());
        p.addToCart();
        p.addToCart();
        p1.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "4");
        header.clickOnTheCartIcon();
        cartPage.withProduct(p);
        cartPage.withProduct(p);
        cartPage.withProduct(p1);
        cartPage.withProduct(product);
        assertEquals(cartPage.getProductsCount(),"3");

    }
    @Test(dataProviderClass = DataProviderForDemoShop.class,dataProvider = "ProductsDetailsDataProviderTest2Less")
    public void whenLoggedInWithDinoUserAndAddingMultipleDifferentProductsToCartCounterMustShowCorrectNumber(Product product) {
        User user = new User("dino","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        Product p = new Product("1","Awesome Granite Chips","15.99");
        Product p1 = new Product("5","Awesome Soft Shirt","29.99");
        new Product(product.getProductId(), product.getName(), product.getPrice());
        p.addToCart();
        p.addToCart();
        p1.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "4");
        header.clickOnTheCartIcon();
        cartPage.withProduct(p);
        cartPage.withProduct(p);
        cartPage.withProduct(p1);
        cartPage.withProduct(product);
        assertEquals(cartPage.getProductsCount(),"3");

    }@Test(dataProviderClass = DataProviderForDemoShop.class,dataProvider = "ProductsDetailsDataProviderTest2Less")
    public void whenLoggedInWithBeetleUserAndAddingMultipleDifferentProductsToCartCounterMustShowCorrectNumber(Product product) {
        User user = new User("beetle","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        Product p = new Product("1","Awesome Granite Chips","15.99");
        Product p1 = new Product("5","Awesome Soft Shirt","29.99");
        new Product(product.getProductId(), product.getName(), product.getPrice());
        p.addToCart();
        p.addToCart();
        p1.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "4");
        header.clickOnTheCartIcon();
        cartPage.withProduct(p);
        cartPage.withProduct(p);
        cartPage.withProduct(p1);
        cartPage.withProduct(product);
        assertEquals(cartPage.getProductsCount(),"3");

    }@Test(dataProviderClass = DataProviderForDemoShop.class,dataProvider = "ProductsDetailsDataProviderTest2Less")
    public void whenLoggedInWithTurtleUserAndAddingMultipleDifferentProductsToCartCounterMustShowCorrectNumber(Product product) {
        User user = new User("turtle","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        Product p = new Product("1","Awesome Granite Chips","15.99");
        Product p1 = new Product("5","Awesome Soft Shirt","29.99");
        new Product(product.getProductId(), product.getName(), product.getPrice());
        p.addToCart();
        p.addToCart();
        p1.addToCart();
        product.addToCart();
        assertEquals(header.getCartCounter(), "4");
        header.clickOnTheCartIcon();
        cartPage.withProduct(p);
        cartPage.withProduct(p);
        cartPage.withProduct(p1);
        cartPage.withProduct(product);
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
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsAndUserDetailsDataProviderTest")
    public void clickOnTheContinueShoppingButtonWhileLoggedInWithDinoUserToGoBackToShoppingPage(Product product){
        User user = new User("dino","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        product.addToCart();
        assertEquals(header.getCartCounter(),"1");
        header.clickOnTheCartIcon();
        cartPage.continueShoppingButton();
        assertEquals(demoShopPage.getTitle(),"Demo shop",
                "When clicking on the continue shopping button shopping page must appear!");

    } @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsAndUserDetailsDataProviderTest")
    public void clickOnTheContinueShoppingButtonWhileLoggedInWithBeetleUserToGoBackToShoppingPage(Product product){
        User user = new User("beetle","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
        product.addToCart();
        assertEquals(header.getCartCounter(),"1");
        header.clickOnTheCartIcon();
        cartPage.continueShoppingButton();
        assertEquals(demoShopPage.getTitle(),"Demo shop",
                "When clicking on the continue shopping button shopping page must appear!");

    } @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsAndUserDetailsDataProviderTest")
    public void clickOnTheContinueShoppingButtonWhileLoggedInWithTurtleUserToGoBackToShoppingPage(Product product){
        User user = new User("turtle","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
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
    public void checkIfTotalAmountItIsTheSameWithSumOfTheProduct(Product product) {
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
        String firstPrice = firstArticlePrice.replaceAll("\\$", "");
        assertEquals(firstPrice,cartPage.totalPrice(),
                "When adding just one product but more quantity the total product price and cart total must be the same");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void checkIfTotalAmountItIsTheSameWithSumOfTheProductLoggedInWithDino(Product product) {
        User user = new User("dino","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
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
        String firstPrice = firstArticlePrice.replaceAll("\\$", "");
        assertEquals(firstPrice,cartPage.totalPrice(),
                "When adding just one product but more quantity the total product price and cart total must be the same");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void checkIfTotalAmountItIsTheSameWithSumOfTheProductLoggedInWithTurtle(Product product) {
        User user = new User("turtle","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
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
        String firstPrice = firstArticlePrice.replaceAll("\\$", "");
        assertEquals(firstPrice,cartPage.totalPrice(),
                "When adding just one product but more quantity the total product price and cart total must be the same");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void checkIfTotalAmountItIsTheSameWithSumOfTheProductLoggedInWithBeetle(Product product) {
        User user = new User("beetle","choochoo");
        header.clickOnTheLoginIcon();
        modal.clickOnTheUsernameField();
        modal.typeInUsername(user.getUserName());
        modal.clickOnThePasswordField();
        modal.typeInPassword(user.getPassword());
        modal.clickOnTheLoginButtonFromModal();
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
        String firstPrice = firstArticlePrice.replaceAll("\\$", "");
        assertEquals(firstPrice,cartPage.totalPrice(),
                "When adding just one product but more quantity the total product price and cart total must be the same");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "ProductsDetailsDataProviderTest")
    public void checkIfTotalAmountItIsTheSameWithSumOfTheProducts_WithDifferentProducts(Product product){
        Product product1 = new Product(product.getProductId(),product.getName(),product.getPrice());
        Product product2 = new Product(product.getProductId(),product.getName(),product.getPrice());
        product1.addToCart();
        product2.addToCart();
        assertEquals(header.getCartCounter(), "2");
        header.clickOnTheCartIcon();
        cartPage.withProduct(product1);
        cartPage.withProduct(product2);
        assertEquals(cartPage.getProductsCount(),"2",
                "When adding 2 different product to cart must be two different rows");
        String total = cartPage.getTotal(product1, product2);
        assertEquals( cartPage.totalPrice(), total,
                "When adding two different product to cart the total ammount and sum of products prices must be equal");


    }


}
