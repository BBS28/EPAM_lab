package com.epam.shchehlov.service;

import com.epam.shchehlov.bean.ProductFilterBean;
import com.epam.shchehlov.entity.Category;
import com.epam.shchehlov.entity.Manufacturer;
import com.epam.shchehlov.entity.Product;

import java.util.List;

/**
 * An interface of service for working with entity product.
 *
 * @author B.Shchehlov.
 */
public interface ProductService {

    List<Product> getAllProducts(ProductFilterBean productFilterBean);

    Product getProduct(long id);

    List<Manufacturer> getAllManufacturers();

    List<Category> getAllCategories();

    int getProductNumber(ProductFilterBean productFilterBean);
}
