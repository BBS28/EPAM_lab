package com.epam.shchehlov.repository.impl;

import com.epam.shchehlov.entity.User;
import com.epam.shchehlov.repository.UserRepository;
import com.epam.shchehlov.repository.template.JdbcTemplate;
import org.apache.log4j.Logger;

import java.util.List;

import static com.epam.shchehlov.util.security.PasswordSecurity.getSecurePassword;

/**
 * Repository for working with entity user using JDBC.
 *
 * @author B.Shchehlov.
 */
public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = Logger.getLogger(UserRepositoryImpl.class);
    private static final String SQL_GET_USER = "SELECT id, login, first_name, last_name, email, password, mailing, role FROM ptshop.users WHERE login = ?";
    private static final String SQL_GET_ALL_USERS = "SELECT login, first_name, last_name, email, password, mailing, role FROM ptshop.users";
    private static final String SQL_CREATE_USER = "INSERT INTO ptshop.users (login, first_name , last_name, email, password, mailing, role) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_USER = "UPDATE ptshop.users SET first_name = ?, last_name = ?, email = ? , password = ? , mailing = ?, role = ? WHERE login = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM ptshop.users WHERE login = ?";

    private final JdbcTemplate<User> jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate<User> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        Object[] values = {};
        return jdbcTemplate.executeGetAll(SQL_GET_ALL_USERS, values);
    }

    @Override
    public User getUser(String login) {
        Object[] values = {login};
        return jdbcTemplate.executeGetOne(SQL_GET_USER, values);
    }

    @Override
    public long createUser(User user) {
        logger.debug("user.getRole() ==> " + user.getRole());
        Object[] values = {user.getLogin(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                getSecurePassword(user.getPassword()),
                user.isMailing(),
                user.getRole().toString()};

        return jdbcTemplate.executeUpdate(SQL_CREATE_USER, values);
    }

    @Override
    public long updateUser(User user) {
        Object[] values = {user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                getSecurePassword(user.getPassword()),
                user.isMailing(),
                user.getRole(),
                user.getLogin()};

        return jdbcTemplate.executeUpdate(SQL_UPDATE_USER, values);
    }

    @Override
    public long deleteUser(String login) {
        Object[] values = {login};
        return jdbcTemplate.executeUpdate(SQL_DELETE_USER, values);
    }

    @Override
    public boolean isExistUser(String login) {
        return getUser(login) != null;
    }
}
