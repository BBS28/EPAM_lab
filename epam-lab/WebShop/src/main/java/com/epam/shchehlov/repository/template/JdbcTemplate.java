package com.epam.shchehlov.repository.template;

import com.epam.shchehlov.database.connection.JdbcConnectionHolder;
import com.epam.shchehlov.exception.DBException;
import com.epam.shchehlov.repository.extractor.EntityExtractor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.epam.shchehlov.constant.Constant.MESSAGE_CANT_EXTRACT_ENTITIES;
import static com.epam.shchehlov.constant.Constant.MESSAGE_CANT_EXTRACT_ENTITY;
import static com.epam.shchehlov.constant.Constant.MESSAGE_CANT_UPDATE_ENTITY;
import static com.epam.shchehlov.util.DBUtils.close;

/**
 * Executes the main JDBC workflow, leaving the application code to provide the SQL and retrieve the results.
 *
 * @author B.Shchehlov.
 */
public class JdbcTemplate<T> {

    private static final Logger logger = Logger.getLogger(JdbcTemplate.class);
    private final EntityExtractor<T> extractor;

    public JdbcTemplate(EntityExtractor<T> extractor) {
        this.extractor = extractor;
    }

    public List<T> executeGetAll(String sqlQuery, Object[] parameters) {
        List<T> entityList = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = JdbcConnectionHolder.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            setPreparedStatement(preparedStatement, parameters);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entityList.add(extractor.extractEntity(resultSet));
            }
            logger.info("Get entity from DB success");

        } catch (SQLException e) {
            logger.error(MESSAGE_CANT_EXTRACT_ENTITIES + ExceptionUtils.getStackTrace(e));
            throw new DBException(MESSAGE_CANT_EXTRACT_ENTITIES + ExceptionUtils.getStackTrace(e));
        } finally {
            close(resultSet);
        }

        return entityList;
    }

    public T executeGetOne(String sqlQuery, Object[] parameters) {
        T entity = null;
        ResultSet resultSet = null;
        Connection connection = JdbcConnectionHolder.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            logger.debug("sqlQuery ==> " + sqlQuery);
            setPreparedStatement(preparedStatement, parameters);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                entity = extractor.extractEntity(resultSet);
            }
            logger.info("Get entity from DB ==> " + entity);

        } catch (SQLException e) {
            logger.error(MESSAGE_CANT_EXTRACT_ENTITY + ExceptionUtils.getStackTrace(e));
            throw new DBException(MESSAGE_CANT_EXTRACT_ENTITY + ExceptionUtils.getStackTrace(e));
        } finally {
            close(resultSet);
        }

        return entity;
    }

    public long executeUpdate(String sqlQuery, Object[] parameters) {
        ResultSet resultSet = null;
        long id = 0;
        Connection connection = JdbcConnectionHolder.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            setPreparedStatement(preparedStatement, parameters);
            if (preparedStatement.executeUpdate() > 0) {
                resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet != null && resultSet.next()) {
                    id = resultSet.getLong(1);
                }
            }

        } catch (SQLException e) {
            logger.error(MESSAGE_CANT_UPDATE_ENTITY + ExceptionUtils.getStackTrace(e));
            throw new DBException(MESSAGE_CANT_UPDATE_ENTITY + ExceptionUtils.getStackTrace(e));
        } finally {
            close(resultSet);
        }

        return id;
    }

    private void setPreparedStatement(PreparedStatement preparedStatement, Object[] parameters) throws SQLException {
        int index = 1;
        logger.debug(parameters);
        for (Object value : parameters) {
            preparedStatement.setObject(index++, value);
        }
    }
}
