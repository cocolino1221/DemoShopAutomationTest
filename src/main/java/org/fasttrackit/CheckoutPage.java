package org.fasttrackit;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {
    private final SelenideElement firstName = $("#first-name");
    private final SelenideElement lastName = $("#last-name");
    private final SelenideElement adress = $("#address");
    private final SelenideElement checkoutPageTitle = $(".text-muted");
    private final SelenideElement completeInformation = $("[data-test=error]");
    private final SelenideElement cancelButton = $(".btn-danger");
    private final SelenideElement continueCheckout = $(".btn-success");

    public String getCheckoutPageTitle() {
        return checkoutPageTitle.getText();
    }

    public void clickFirstNameField() {
        firstName.click();
    }

    public void clickLastName() {
        lastName.click();
    }

    public void clickAdressField() {
        adress.click();
    }

    public void typeOnTheFirstNameField(String wordToType) {
        this.firstName.sendKeys(wordToType);
    }

    public void typeOnTheLastNameField(String wordToType) {
        this.lastName.sendKeys(wordToType);
    }

    public void typeOnTheAdressField(String wordToType) {
        this.adress.sendKeys(wordToType);
    }

    public boolean validateFirstNameIsDisplayedAndEnabled() {
        return firstName.isDisplayed() && firstName.isEnabled();
    }

    public boolean validateLastNameisDisplayedAndEnabled() {
        return lastName.isDisplayed() && lastName.isEnabled();
    }

    public boolean completeInformationIsDisplayed() {
        return completeInformation.isDisplayed();
    }

    public void continueCheckout() {
        continueCheckout.click();
    }

    public void cancelOrder() {
        cancelButton.click();
    }


}
