package org.fasttrackit.dataprovider;

import org.fasttrackit.Product;
import org.testng.annotations.DataProvider;

public class DataProviderForDemoShop {
    @DataProvider(name = "UserDataProviderTest")
    public Object[] createUserData(){
        return new Object[]{
                new User("dino", "choochoo"),
                new User("turtle", "choochoo"),
                new User("beetle","choochoo")

        };
    }
    @DataProvider(name = "InvalidUserDataProviderTest")
    public Object[] createInvalidUserData(){
        return new Object[]{
                new InvalidUser("locked", "choochoo","The user has been locked out."),
                new InvalidUser("locked", "","Please fill in the password!"),
                new InvalidUser("","choochoo","Please fill in the username!"),
                new InvalidUser("dino","","Please fill in the password!"),
                new InvalidUser("locked","12345","Incorrect username or password!")

        };
    }
    @DataProvider(name = "ProductsDetailsDataProviderTest")
    public static Object[] createProductDetails(){
        return new Object[]{
                new Product("1","Awesome Granite Chips","15.99"),
                new Product("3","Awesome Metal Chair","15.99"),
                new Product("5","Awesome Soft Shirt","29.99"),
                new Product("9","Gorgeous Soft Pizza", "19.99"),
                new Product("2","Incredible Concrete Hat","7.99"),
                new Product("8","Licensed Steel Gloves","14.99"),
                new Product("7","Practical Metal Mouse", "9.99"),
                new Product("4","Practical Wooden Bacon", "29.99"),
                new Product("6","Practical Wooden Bacon", "1.99"),
                new Product("0","Refined Frozen Mouse", "9.99")
        };
    }
    @DataProvider(name = "ProductsDetailsDataProviderTest1")
    public static Object[] createProductDetailsWithoutFirstProduct() {
        return new Object[]{

                new Product("3", "Awesome Metal Chair", "15.99"),
                new Product("5", "Awesome Soft Shirt", "29.99"),
                new Product("9", "Gorgeous Soft Pizza", "19.99"),
                new Product("2", "Incredible Concrete Hat", "7.99"),
                new Product("8", "Licensed Steel Gloves", "14.99"),
                new Product("7", "Practical Metal Mouse", "9.99"),
                new Product("4", "Practical Wooden Bacon", "29.99"),
                new Product("6", "Practical Wooden Bacon", "1.99"),
                new Product("0", "Refined Frozen Mouse", "9.99")
        };
    }
    @DataProvider(name = "ProductsDetailsDataProviderTest2Less")
    public static Object[] createProductDetailsWithoutFirstTwoProduct() {
        return new Object[]{
                new Product("3", "Awesome Metal Chair", "15.99"),
                new Product("9", "Gorgeous Soft Pizza", "19.99"),
                new Product("2", "Incredible Concrete Hat", "7.99"),
                new Product("8", "Licensed Steel Gloves", "14.99"),
                new Product("7", "Practical Metal Mouse", "9.99"),
                new Product("4", "Practical Wooden Bacon", "29.99"),
                new Product("6", "Practical Wooden Bacon", "1.99"),
                new Product("0", "Refined Frozen Mouse", "9.99")
        };
    }
    @DataProvider(name = "ProductsDetailsForSortBar")
    public Object[] createProductsForSortBar() {
        return new Object[]{
                new Product("1", "Awesome Granite Chips", "15.99"),
                new Product("3", "Awesome Metal Chair", "15.99"),
                new Product("5", "Awesome Soft Shirt", "29.99"),
                new Product("9", "Gorgeous Soft Pizza", "19.99"),
                new Product("2", "Incredible Concrete Hat", "7.99"),
                new Product("8", "Licensed Steel Gloves", "14.99"),
                new Product("7", "Practical Metal Mouse", "9.99"),
                new Product("4", "Practical Wooden Bacon", "29.99"),
                new Product("6", "Practical Wooden Bacon", "1.99"),
                new Product("0", "Refined Frozen Mouse", "9.99")
        };
    }
        @DataProvider(name = "ProductsAndUserDetailsDataProviderTest")
        public Object[] createProductDetailsAndUserToTest(){
            return new Object[]{
                    new Product("1","Awesome Granite Chips","15.99"),
                    new Product("3","Awesome Metal Chair","15.99"),
                    new Product("5","Awesome Soft Shirt","29.99"),
                    new Product("9","Gorgeous Soft Pizza", "19.99"),
                    new Product("2","Incredible Concrete Hat","7.99"),
                    new Product("8","Licensed Steel Gloves","14.99"),
                    new Product("7","Practical Metal Mouse", "9.99"),
                    new Product("4","Practical Wooden Bacon", "29.99"),
                    new Product("6","Practical Wooden Bacon", "1.99"),
                    new Product("0","Refined Frozen Mouse", "9.99")
            };
    }

}
