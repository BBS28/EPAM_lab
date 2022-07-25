package com.epam.shchehlov.bean;

import java.util.List;

/**
 * Class for temporary storage of data entered by the user to filter the display of products.
 *
 * @author B.Shchehlov.
 */
public class ProductFilterBean {

    private String name;
    private Integer minPrice;
    private Integer maxPrice;
    private List<String> manufacturers;
    private List<String> categories;
    private int productsOnPage;
    private String sortField;
    private boolean isAscending;
    private int pageNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<String> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<String> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public int getProductsOnPage() {
        return productsOnPage;
    }

    public void setProductsOnPage(int productsOnPage) {
        this.productsOnPage = productsOnPage;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public boolean isAscending() {
        return isAscending;
    }

    public void setAscending(boolean ascending) {
        isAscending = ascending;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return "ProductFilterBean{" +
                "name='" + name + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", manufacturers=" + manufacturers +
                ", categories=" + categories +
                ", productsOnPage=" + productsOnPage +
                ", sortField='" + sortField + '\'' +
                ", isAscending=" + isAscending +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
