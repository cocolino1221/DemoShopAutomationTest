package org.fasttrackit;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class ProductInCart {
    private final SelenideElement link;
    private final String name;
    private SelenideElement parentRow;
    private final Product product;
    private final SelenideElement decrement;
    private final SelenideElement increment;
    private final SelenideElement deleteFromCart;
    private final SelenideElement productManagerSection;

    private final ElementsCollection columns;



    public ProductInCart(Product product) {

        this.product = product;
        String productId = String.format("#item_%s_title_link", product.getProductId());
        this.link = $(productId);
        this.name = link.getText();
        this.parentRow = link.parent().parent();
        this.decrement = this.parentRow.$(".fa-minus-circle");
        this.increment = this.parentRow.$(".fa-plus-circle");
        this.deleteFromCart = this.parentRow.$(".fa-trash");
        this.productManagerSection = parentRow.$(".col-md-auto");
        this.columns = this.parentRow.$$(".col-md-auto");


    }
    public String unitPrice(){
        return columns.get(1).getText().replaceAll("\\$","");
    }

    public void deleteFromCart() {
        deleteFromCart.click();
    }

    public void reduceAmount() {
        decrement.click();
    }

    public void increaseAmount() {
        increment.click();
    }

    public String getCount() {
        return columns.first().getText();
    }
    public String getTotalProductPrice(){
        return columns.get(2).getText().replaceAll("\\$","");
    }
    public String getProductId(){
        return this.link.getText();

    }




}
