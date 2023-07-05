package org.fasttrackit;

public class ProductInCart {
    private int count = 1;
    private final Product product;
    private final String deleteFromCart = " Remove from cart ";

    public ProductInCart( Product product) {

        this.product = product;
    }
    public void clickOnTheDeleteFromCart(){
        System.out.println("Click on the " + deleteFromCart + " icon on " + product.getName());
    }

    public void addOneMoreProductInCart() {
        count++;
    }
}
