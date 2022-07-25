package com.epam.shchehlov.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {

    private final Map<Product, Integer> cartMap;

    public Cart() {
        cartMap = new LinkedHashMap<>();
    }

    public void addProduct(Product product, int amount) {
        cartMap.put(product, amount);
    }

    public void deleteProduct(Product product) {
        cartMap.remove(product);
    }

    public void clearCart() {
        cartMap.clear();
    }

    public List<Product> getProductList() {
        return new ArrayList<>(cartMap.keySet());
    }

    public int getProductAmount(Product product) {
        return cartMap.get(product);
    }

    public int getAllProductsAmount() {
        return cartMap.values()
                .stream()
                .reduce(0, Integer::sum);
    }

    public int getTotalPrice() {
        return cartMap.entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrice() * entry.getValue())
                .reduce(0, Integer::sum);
    }

    public List<OrderedProduct> getOrderedProductsList() {
        List<OrderedProduct> orderedProductsList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            OrderedProduct product = new OrderedProduct(
                    entry.getKey().getId(),
                    entry.getKey().getName(),
                    entry.getValue(),
                    entry.getKey().getPrice());
            orderedProductsList.add(product);
        }
        return orderedProductsList;
    }

    @Override
    public String toString() {
        return "Cart{" + cartMap + '}';
    }
}
