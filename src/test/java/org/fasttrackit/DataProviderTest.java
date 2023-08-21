package org.fasttrackit;

import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.fasttrackit.dataprovider.User;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataProviderTest {
    @Test(dataProvider = "UserDataProviderTest", dataProviderClass = DataProviderForDemoShop.class)
    public void testMyDataProvider(User user){
        assertTrue(user.getGreetingsMessage().contains(user.getUserName()),"Expected Name must be displayed");
    }
}
