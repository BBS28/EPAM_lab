package com.epam.shchehlov.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.epam.shchehlov.constant.Constant.EMPTY_STRING;
import static com.epam.shchehlov.constant.Constant.LOG_IN_ERROR_MESSAGE;
import static com.epam.shchehlov.constant.Constant.MESSAGE_LOGIN_FIELDS_EMPTY;
import static com.epam.shchehlov.constant.Constant.USER_LOGIN;
import static com.epam.shchehlov.constant.Constant.USER_PASSWORD;

/**
 * Class for handling login process.
 *
 * @author B.Shchehlov.
 */
public class LoginHandler {

    private static final Logger logger = Logger.getLogger(LoginHandler.class);

    private LoginHandler() {
    }

    /**
     * Validates login for empty values.
     *
     * @param request httpRequest.
     * @return true if input valid.
     */
    public static boolean validateEmptyLogin(HttpServletRequest request) {
        logger.debug("request.getParameter(USER_LOGIN) ==> " + request.getParameter(USER_LOGIN));
        logger.debug("request.getParameter(USER_PASSWORD) ==> " + request.getParameter(USER_PASSWORD));
        logger.debug("request.getParameter(USER_PASSWORD).equals(\"\")  ==> " + request.getParameter(USER_PASSWORD).equals(""));
        if (request.getParameter(USER_LOGIN) == null
                || request.getParameter(USER_PASSWORD) == null
                || request.getParameter(USER_LOGIN).equals(EMPTY_STRING)
                || request.getParameter(USER_PASSWORD).equals(EMPTY_STRING)) {
            request.getSession().setAttribute(LOG_IN_ERROR_MESSAGE, MESSAGE_LOGIN_FIELDS_EMPTY);
            return false;
        }
        return true;
    }
}
