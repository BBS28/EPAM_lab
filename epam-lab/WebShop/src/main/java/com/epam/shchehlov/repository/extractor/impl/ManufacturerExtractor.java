package com.epam.shchehlov.repository.extractor.impl;

import com.epam.shchehlov.entity.Manufacturer;
import com.epam.shchehlov.repository.extractor.EntityExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.shchehlov.constant.Constant.ID;
import static com.epam.shchehlov.constant.Constant.NAME;

public class ManufacturerExtractor implements EntityExtractor<Manufacturer> {

    @Override
    public Manufacturer extractEntity(ResultSet resultSet) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getLong(ID));
        manufacturer.setName(resultSet.getString(NAME));
        return manufacturer;
    }
}
