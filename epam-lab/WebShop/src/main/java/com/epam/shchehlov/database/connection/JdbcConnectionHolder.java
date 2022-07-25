package com.epam.shchehlov.database.connection;

import java.sql.Connection;

/**
 * JDBC Connection holder.
 *
 * @author B.Shchehlov.
 */
public class JdbcConnectionHolder {

    private static final ThreadLocal<Connection> holder = new ThreadLocal<>();

    public static void setConnection(Connection connection) {
        holder.set(connection);
    }

    public static Connection getConnection() {
        return holder.get();
    }

    public static void removeConnection() {
        holder.remove();
    }
}
