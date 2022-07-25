package com.epam.shchehlov.entity;

import java.util.Objects;

/**
 * Class for entity Product.
 *
 * @author B.Shchehlov.
 */
public class Product extends Entity {

    private String name;
    private String manufacturerName;
    private String categoryName;
    private int price;
    private String description;

    public Product() {
    }

    public Product(String name,
                   String manufacturerName,
                   String categoryName,
                   int price,
                   String description) {
        this.name = name;
        this.manufacturerName = manufacturerName;
        this.categoryName = categoryName;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Product)) {
            return false;
        }
        Product product = (Product) object;
        return price == product.price
                && Objects.equals(name, product.name)
                && Objects.equals(manufacturerName, product.manufacturerName)
                && Objects.equals(categoryName, product.categoryName)
                && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufacturerName, categoryName, price, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
