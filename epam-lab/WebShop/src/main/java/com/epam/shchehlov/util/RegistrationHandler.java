package com.epam.shchehlov.util;

import com.epam.shchehlov.bean.UserRegistrationBean;
import com.epam.shchehlov.entity.attribute.Role;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.epam.shchehlov.constant.Constant.INVALID_USER_EMAIL;
import static com.epam.shchehlov.constant.Constant.INVALID_USER_FIRST_NAME;
import static com.epam.shchehlov.constant.Constant.INVALID_USER_LAST_NAME;
import static com.epam.shchehlov.constant.Constant.INVALID_USER_LOGIN;
import static com.epam.shchehlov.constant.Constant.INVALID_USER_PASSWORD;
import static com.epam.shchehlov.constant.Constant.REGEX_USER_EMAIL;
import static com.epam.shchehlov.constant.Constant.REGEX_USER_LOGIN;
import static com.epam.shchehlov.constant.Constant.REGEX_USER_NAME;
import static com.epam.shchehlov.constant.Constant.REGEX_USER_PASSWORD;
import static com.epam.shchehlov.constant.Constant.USER_CAPTCHA;
import static com.epam.shchehlov.constant.Constant.USER_EMAIL;
import static com.epam.shchehlov.constant.Constant.USER_FIRST_NAME;
import static com.epam.shchehlov.constant.Constant.USER_LAST_NAME;
import static com.epam.shchehlov.constant.Constant.USER_LOGIN;
import static com.epam.shchehlov.constant.Constant.USER_MAILING;
import static com.epam.shchehlov.constant.Constant.USER_PASSWORD;
import static java.util.Objects.isNull;

/**
 * Class for handling user registration.
 *
 * @author B.Shchehlov.
 */
public class RegistrationHandler {

    private static final Logger logger = Logger.getLogger(RegistrationHandler.class);

    private RegistrationHandler() {
    }

    /**
     * Saves incorrectly entered data to the container during registration.
     *
     * @param userRegistrationBean an instance of the class that stores the values entered by the user.
     * @return Map of incorrectly entered data to the container during registration.
     */
    public static Map<String, String> validateUserBean(UserRegistrationBean userRegistrationBean) {
        Map<String, String> errors = new LinkedHashMap<>();

        if (isNull(userRegistrationBean.getFirstName()) || !userRegistrationBean.getFirstName().matches(REGEX_USER_NAME)) {
            errors.put(USER_FIRST_NAME, INVALID_USER_FIRST_NAME);
        }
        if (isNull(userRegistrationBean.getLastName()) || !userRegistrationBean.getLastName().matches(REGEX_USER_NAME)) {
            errors.put(USER_LAST_NAME, INVALID_USER_LAST_NAME);
        }
        if (isNull(userRegistrationBean.getEmail()) || !userRegistrationBean.getEmail().matches(REGEX_USER_EMAIL)) {
            errors.put(USER_EMAIL, INVALID_USER_EMAIL);
        }
        if (isNull(userRegistrationBean.getLogin()) || !userRegistrationBean.getLogin().matches(REGEX_USER_LOGIN)) {
            errors.put(USER_LOGIN, INVALID_USER_LOGIN);
        }
        if (isNull(userRegistrationBean.getPassword()) || !userRegistrationBean.getPassword().matches(REGEX_USER_PASSWORD)) {
            errors.put(USER_PASSWORD, INVALID_USER_PASSWORD);
        }

        return errors;
    }

    /**
     * Returns an instance of the UserRegistrationBean that stores the values entered by the user.
     *
     * @param request HttpServletRequest.
     * @return an instance of the UserRegistrationBean that stores the values entered by the user.
     */
    public static UserRegistrationBean getUserRegistrationBean(HttpServletRequest request) {
        logger.debug(request.getParameter(USER_MAILING));

        return new UserRegistrationBean(request.getParameter(USER_FIRST_NAME),
                request.getParameter(USER_LAST_NAME),
                request.getParameter(USER_EMAIL),
                request.getParameter(USER_LOGIN),
                request.getParameter(USER_PASSWORD),
                request.getParameter(USER_MAILING) != null,
                request.getParameter(USER_CAPTCHA),
                Role.USER);
    }
}
