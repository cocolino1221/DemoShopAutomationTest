package org.fasttrackit;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class SortBarPage {
    private final SelenideElement sortBar = $(".sort-products-select");
    private final SelenideElement sortAToZ = $("[value=az]");
    private final SelenideElement sortZToA = $("[value=za]");
    private final SelenideElement sortLowToHigh = $("[value=lohi]");
    private final SelenideElement sortHighToLow = $("[value=hilo]");
    public boolean sortBarIsEnable(){
        return sortBar.isEnabled();
    }
    public void sortBarClick(){
        sortBar.click();
    }
    public boolean validateSortAToZIsEnabledAndDisplayed() {
        return sortAToZ.isDisplayed() &&sortAToZ.isEnabled();
    }

    public boolean validateSortZToAIsEnabledAndDisplayed() {
        return sortZToA.isDisplayed() &&sortZToA.isEnabled();
    }

    public boolean validateSortByPriceLowToHighIsEnabledAndDisplayed() {
        return sortLowToHigh.isDisplayed() &&sortLowToHigh.isEnabled();
    }
    public boolean  validateSortByPriceHighToLowIsEnabledAndDisplayed(){
        return sortHighToLow.isDisplayed() &&sortHighToLow.isEnabled();
    }

    public void clickAToZSort(){
        sortAToZ.click();
    }
    public void clickZToASort(){
        sortZToA.click();
    }
    public void clickLowToHighSort(){
        sortLowToHigh.click();
    }
    public void clickHighToLowSort(){
        sortHighToLow.click();
    }


}
