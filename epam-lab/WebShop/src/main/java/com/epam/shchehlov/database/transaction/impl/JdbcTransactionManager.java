package com.epam.shchehlov.database.transaction.impl;

import com.epam.shchehlov.database.TransactionOperation;
import com.epam.shchehlov.database.connection.JdbcConnectionHolder;
import com.epam.shchehlov.database.transaction.TransactionManager;
import com.epam.shchehlov.exception.DBException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static com.epam.shchehlov.constant.Constant.MESSAGE_CANT_CLOSE_CONNECTION;
import static com.epam.shchehlov.constant.Constant.MESSAGE_CANT_ROLLBACK;
import static com.epam.shchehlov.constant.Constant.MESSAGE_TRANSACTION_ERROR;
import static com.epam.shchehlov.constant.Constant.MESSAGE_TRANSACTION_INQUIRY_FAILED;
import static com.epam.shchehlov.constant.Constant.MESSAGE_TRANSACTION_MANIPULATION_FAILED;

/**
 * The TransactionManager implementation for JDBC.
 *
 * @author B.Shchehlov.
 */
public class JdbcTransactionManager implements TransactionManager {

    private static final Logger logger = Logger.getLogger(JdbcTransactionManager.class);
    private final DataSource dataSource;

    public JdbcTransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <T> T doInquiryTransaction(TransactionOperation<T> transactionOperation) {
        T result;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            JdbcConnectionHolder.setConnection(connection);
            result = transactionOperation.execute();
        } catch (SQLException e) {
            logger.error(MESSAGE_TRANSACTION_INQUIRY_FAILED + ExceptionUtils.getStackTrace(e));
            throw new DBException(MESSAGE_TRANSACTION_ERROR, e);
        } finally {
            close(connection);
        }
        return result;
    }

    @Override
    public <T> T doManipulationTransaction(TransactionOperation<T> transactionOperation) {
        T result;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            JdbcConnectionHolder.setConnection(connection);
            result = transactionOperation.execute();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            logger.error(MESSAGE_TRANSACTION_MANIPULATION_FAILED + ExceptionUtils.getStackTrace(e));
            throw new DBException(MESSAGE_TRANSACTION_ERROR, e);
        } finally {
            JdbcConnectionHolder.removeConnection();
            close(connection);
        }
        return result;
    }

    /**
     * Closes the connection.
     */
    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(MESSAGE_CANT_CLOSE_CONNECTION + ExceptionUtils.getStackTrace(e));
            }
        }
    }

    /**
     * Rolls back changes in the database to the state before the transaction.
     */
    private void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error(MESSAGE_CANT_ROLLBACK + ExceptionUtils.getStackTrace(e));
            }
        }
    }
}
