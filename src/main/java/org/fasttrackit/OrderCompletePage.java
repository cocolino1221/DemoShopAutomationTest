package org.fasttrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class OrderCompletePage {
    private final SelenideElement continueShopping = $(".btn-success");


    public void clickContinueShopping (){
        continueShopping.click();
    }
}
