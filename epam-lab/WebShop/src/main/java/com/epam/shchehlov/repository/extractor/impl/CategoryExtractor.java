package com.epam.shchehlov.repository.extractor.impl;

import com.epam.shchehlov.entity.Category;
import com.epam.shchehlov.repository.extractor.EntityExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.shchehlov.constant.Constant.ID;
import static com.epam.shchehlov.constant.Constant.NAME;

public class CategoryExtractor implements EntityExtractor<Category> {

    @Override
    public Category extractEntity(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong(ID));
        category.setName(resultSet.getString(NAME));
        return category;
    }
}
