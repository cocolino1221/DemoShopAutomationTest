package org.fasttrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SummaryOrderPage {
    private final SelenideElement summaryPageTitle = $(".text-muted");
    private final SelenideElement completeCheckoutButton = $("[href='#/checkout-complete']");
    private final SelenideElement cancelOrder = $(".btn-danger");
    private final SelenideElement thankyouMessage = $(".text-center");
    public String summaryPageTitle(){
        return summaryPageTitle.getText();
    }
    public void completeOrder(){
        completeCheckoutButton.click();
    }
    public void cancelOrder(){
        cancelOrder.click();
    }
    public String thankyouMessage(){
        return thankyouMessage.getText();
    }
    public boolean validateThankyouyMessage(){
        return thankyouMessage.isDisplayed();
    }
}
