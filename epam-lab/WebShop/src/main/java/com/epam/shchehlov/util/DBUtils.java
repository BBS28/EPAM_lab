package com.epam.shchehlov.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for handling databases.
 *
 * @author B.Shchehlov.
 */
public class DBUtils {

    private static final Logger logger = Logger.getLogger(DBUtils.class);

    private DBUtils() {
    }

    /**
     * Connects the application to the database.
     *
     * @return DataSource.
     */
    public static DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            Context context = (Context) new InitialContext().lookup("java:comp/env");
            dataSource = (DataSource) context.lookup("jdbc/PT_shop_db");
            logger.info("dataSource ==> " + dataSource.toString());
        } catch (NamingException e) {
            logger.error("Can't get data source" + ExceptionUtils.getStackTrace(e));
        }
        return dataSource;
    }

    /**
     * Closes result set.
     *
     * @param resultSet ResultSet.
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Can't close result set" + ExceptionUtils.getStackTrace(e));
            }
        }
    }
}
