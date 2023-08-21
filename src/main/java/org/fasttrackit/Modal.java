package org.fasttrackit;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.$;

public class Modal {
    private final SelenideElement modalTitle = $(".modal-title");
    private final SelenideElement username = $("#user-name");
    private final SelenideElement password = $("#password");
    private final SelenideElement modalLoginButton = $("[type=submit]");
    private final SelenideElement error = $(".error");

    @Step("Click on the username field")
    public void clickOnTheUsernameField() {
        username.click();
    }
    @Step("Type in {username}")
    public void typeInUsername(String userName) {
        username.setValue(userName);

    }

    public void clickOnThePasswordField() {
        password.click();
    }

    public void typeInPassword(String password) {
        this.password.setValue(password);
    }
    public void clickOnTheLoginButtonFromModal(){
        modalLoginButton.click();
    }
    public void loginModal() {
        Header loginModal = new Header();
        loginModal.clickOnTheLoginIcon();
    }

    public String getModalTitle() {
       return modalTitle.text();
    }
    public String getErrorMessage(){
        return error.text();
    }



}