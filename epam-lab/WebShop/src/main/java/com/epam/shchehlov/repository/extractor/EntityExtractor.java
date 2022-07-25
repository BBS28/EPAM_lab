package com.epam.shchehlov.repository.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityExtractor<T> {

    /**
     * Creates entity from ResultSet.
     *
     * @param resultSet ResultSet.
     * @return Entity inheritor.
     * @throws SQLException
     */
    T extractEntity(ResultSet resultSet) throws SQLException;
}
