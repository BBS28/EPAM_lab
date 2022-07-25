package com.epam.shchehlov.repository.extractor.impl;

import com.epam.shchehlov.entity.Product;
import com.epam.shchehlov.repository.extractor.EntityExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.shchehlov.constant.Constant.CATEGORY_NAME;
import static com.epam.shchehlov.constant.Constant.DESCRIPTION;
import static com.epam.shchehlov.constant.Constant.ID;
import static com.epam.shchehlov.constant.Constant.MANUFACTURER_NAME;
import static com.epam.shchehlov.constant.Constant.NAME;
import static com.epam.shchehlov.constant.Constant.PRICE;

public class ProductExtractor implements EntityExtractor<Product> {

    @Override
    public Product extractEntity(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong(ID));
        product.setName(resultSet.getString(NAME));
        product.setManufacturerName(resultSet.getString(MANUFACTURER_NAME));
        product.setCategoryName(resultSet.getString(CATEGORY_NAME));
        product.setDescription(resultSet.getString(DESCRIPTION));
        product.setPrice(resultSet.getInt(PRICE));
        return product;
    }
}
