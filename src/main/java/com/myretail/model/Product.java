package com.myretail.model;

public class Product {

    //@JsonProperty("productId")
    private int productId;

    //@JsonProperty("name")
    private String name;

    private CurrentPrice currentPrice;

    public Product() {

    }

    public Product(int productId, String name, CurrentPrice currentPrice) {
        this.setProductId(productId);
        this.setName(name);
        this.setCurrentPrice(currentPrice);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrentPrice getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(CurrentPrice currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "Product: {"
                + "id = " + productId + ","
                + "name = " + name + ","
                + "currentPrice = " + currentPrice + "}";
    }
}
