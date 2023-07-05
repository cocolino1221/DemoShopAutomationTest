package org.fasttrackit;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Modal {
    private final SelenideElement modalTitle = $(".modal-title");
    private final SelenideElement usernameField = $("#user-name");

    public void clickOnTheUsernameField() {

        System.out.println("Click on the username field");
    }

    public void typeInUsername(String username) {
        System.out.println("Type in : " + username);
    }

    public void clickOnThePasswordField() {

        System.out.println("Click on the password field");
    }

    public void typeInPassword(String password) {
        System.out.println("Type in : " + password);
    }
    public void loginModal() {
        Header loginModal = new Header();
        loginModal.clickOnTheLoginIcon();
    }

    public String getModalTitle() {
       return modalTitle.text();
    }



}