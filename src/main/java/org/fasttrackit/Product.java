package org.fasttrackit;

public class Product {
    private final String name;
    private final Double Price;

    public Product(String name, Double price) {
        this.name = name;

        this.Price = price;
    }


    public void addToCart() {
        //Click on the add to cart icon
        System.out.println("Click on the add to cart icon ");

    }

    public String getName() {
        return name;

    }

    public Double getPrice() {
        return Price;
    }
}
