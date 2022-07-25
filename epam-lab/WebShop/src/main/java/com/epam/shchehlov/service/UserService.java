package com.epam.shchehlov.service;

import com.epam.shchehlov.entity.User;

import java.util.List;

/**
 * An interface of service for working with entity user.
 *
 * @author B.Shchehlov.
 */
public interface UserService {

    /**
     * returns list of users.
     *
     * @return list of users.
     */
    List<User> getAllUsers();

    /**
     * Returns user by login.
     *
     * @param login of user.
     * @return user by login.
     */
    User getUser(String login);

    /**
     * Return created user.
     *
     * @param user User.
     * @return id of created user.
     */
    long createUser(User user);

    /**
     * Makes changes to an existing user.
     *
     * @param user with changed parameters.
     * @return id of updated user.
     */
    long updateUser(User user);

    /**
     * Deletes user.
     *
     * @param login of user to delete.
     * @return id of deleted user.
     */
    long deleteUser(String login);

    /**
     * Checks if the user exists in the database.
     *
     * @param login of user.
     * @return true if user exists.
     */
    boolean isExistUser(String login);
}
