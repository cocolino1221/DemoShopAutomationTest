package org.fasttrackit;

import com.codeborne.selenide.Selenide;
import org.fasttrackit.config.TestConfig;
import org.fasttrackit.dataprovider.DataProviderForDemoShop;
import org.fasttrackit.dataprovider.InvalidUser;
import org.fasttrackit.dataprovider.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;

public class AuthenticationTest extends TestConfig {
    Page demoShopPage;
    Header header;
    Footer footer;
    Modal loginModal;
    @BeforeTest
    public void setup() {
        demoShopPage = new Page();
        demoShopPage.openPage();
        header = new Header();
        footer = new Footer();
        loginModal = new Modal();
    }
    @AfterMethod
    public void cleanUp(){
        Selenide.refresh();
        footer.reset();
        header.homePage();
    }
    @Test
    public void whenClickingOnLoginButton_LoginModalIsOpened() {
        header.clickOnTheLoginIcon();
        boolean modalDisplayed = demoShopPage.isModalDialogDisplayed();
        Assert.assertTrue(modalDisplayed, "When clicking on the login button ,Login modal must be displayed");
    }

    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "UserDataProviderTest", description = "Login with valid user ,Greetings message is displayed")
    public void checkLoginFunction_WithValidCredentialsTurtle(User user) {
        header.clickOnTheLoginIcon();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        String greetingsMsg = header.getGreetingsMessage();
        assertEquals(greetingsMsg, user.getGreetingsMessage(), String.format("When login with valid username greetings message show Hi %s!",user.getUserName()));
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class , dataProvider = "InvalidUserDataProviderTest", description = "Login with invalid credentials")
    public void checkLoginFunctionWithInvalidCredentials(InvalidUser invalidUser){
        header.clickOnTheLoginIcon();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(invalidUser.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(invalidUser.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        assertEquals(loginModal.getErrorMessage(), invalidUser.getErrorValidationMessage() , "When a credential is not correct the message must be displayed");
    }
    @Test(dataProviderClass = DataProviderForDemoShop.class, dataProvider = "UserDataProviderTest",description = "When click logout button hello guest message must be displayed")
    public void checkLogoutFunction(User user) {
        header.clickOnTheLoginIcon();
        loginModal.clickOnTheUsernameField();
        loginModal.typeInUsername(user.getUserName());
        loginModal.clickOnThePasswordField();
        loginModal.typeInPassword(user.getPassword());
        loginModal.clickOnTheLoginButtonFromModal();
        header.clickOnTheLogoutButton();
        String greetingsMsg = header.getGreetingsMessage();
        assertEquals(greetingsMsg, header.getGreetingsMessage(), "When opening page, Hello guest! greetings message must be displayed");


    }


}
