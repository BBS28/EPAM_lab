package com.epam.shchehlov.entity;

import java.io.Serializable;

public final class OrderedProduct implements Serializable {

    private final long productId;
    private final String productName;
    private final int numberOfProducts;
    private final int productPrice;

    public OrderedProduct(long productId, String productName, int numberOfProducts, int productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.numberOfProducts = numberOfProducts;
        this.productPrice = productPrice;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getProductTotalPrice() {
        return getProductPrice() * getNumberOfProducts();
    }

}
