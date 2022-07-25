package com.epam.shchehlov.repository.extractor.impl;

import com.epam.shchehlov.entity.User;
import com.epam.shchehlov.entity.attribute.Role;
import com.epam.shchehlov.repository.extractor.EntityExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.shchehlov.constant.Constant.USER_EMAIL;
import static com.epam.shchehlov.constant.Constant.USER_FIRST_NAME;
import static com.epam.shchehlov.constant.Constant.USER_ID;
import static com.epam.shchehlov.constant.Constant.USER_LAST_NAME;
import static com.epam.shchehlov.constant.Constant.USER_LOGIN;
import static com.epam.shchehlov.constant.Constant.USER_MAILING;
import static com.epam.shchehlov.constant.Constant.USER_PASSWORD;
import static com.epam.shchehlov.constant.Constant.USER_ROLE;

public class UserExtractor implements EntityExtractor<User> {

    @Override
    public User extractEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(USER_ID));
        user.setLogin(resultSet.getString(USER_LOGIN));
        user.setFirstName(resultSet.getString(USER_FIRST_NAME));
        user.setLastName(resultSet.getString(USER_LAST_NAME));
        user.setEmail(resultSet.getString(USER_EMAIL));
        user.setPassword(resultSet.getString(USER_PASSWORD));
        user.setMailing(!resultSet.getString(USER_MAILING).equals("0"));
        user.setRole(Role.valueOf(resultSet.getString(USER_ROLE)));
        return user;
    }
}
