package com.epam.shchehlov.repository.extractor.impl;

import com.epam.shchehlov.repository.extractor.EntityExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.shchehlov.constant.Constant.PRODUCT_NUMBER;

public class ProductNumbersExtractor implements EntityExtractor<Integer> {

    @Override
    public Integer extractEntity(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(PRODUCT_NUMBER);
    }
}
