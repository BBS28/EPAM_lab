package com.epam.shchehlov.servlet;

import com.epam.shchehlov.bean.UserRegistrationBean;
import com.epam.shchehlov.capatcha.CaptchaService;
import com.epam.shchehlov.service.UserService;
import com.epam.shchehlov.util.AvatarHandler;
import com.epam.shchehlov.util.CaptchaHandler;
import com.epam.shchehlov.util.RegistrationHandler;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.epam.shchehlov.constant.Constant.CAPTCHA_STRATEGY;
import static com.epam.shchehlov.constant.Constant.ERROR_EMAIL;
import static com.epam.shchehlov.constant.Constant.ERROR_FIRST_NAME;
import static com.epam.shchehlov.constant.Constant.ERROR_LAST_NAME;
import static com.epam.shchehlov.constant.Constant.ERROR_LOGIN;
import static com.epam.shchehlov.constant.Constant.ERROR_MESSAGE;
import static com.epam.shchehlov.constant.Constant.ERROR_PASSWORD;
import static com.epam.shchehlov.constant.Constant.INFO_MESSAGE;
import static com.epam.shchehlov.constant.Constant.MESSAGE_INVALID_INPUT;
import static com.epam.shchehlov.constant.Constant.MESSAGE_LOGIN_EXISTS;
import static com.epam.shchehlov.constant.Constant.MESSAGE_LOGIN_EXISTS_FORMAT;
import static com.epam.shchehlov.constant.Constant.PAGE_NAME_HOME;
import static com.epam.shchehlov.constant.Constant.PAGE_NAME_REGISTRATION;
import static com.epam.shchehlov.constant.Constant.REGISTRATION_PAGE;
import static com.epam.shchehlov.constant.Constant.TEMP_EMAIL;
import static com.epam.shchehlov.constant.Constant.TEMP_FIRST_NAME;
import static com.epam.shchehlov.constant.Constant.TEMP_LAST_NAME;
import static com.epam.shchehlov.constant.Constant.TEMP_LOGIN;
import static com.epam.shchehlov.constant.Constant.USER_CAPTCHA;
import static com.epam.shchehlov.constant.Constant.USER_EMAIL;
import static com.epam.shchehlov.constant.Constant.USER_FIRST_NAME;
import static com.epam.shchehlov.constant.Constant.USER_LAST_NAME;
import static com.epam.shchehlov.constant.Constant.USER_LOGIN;
import static com.epam.shchehlov.constant.Constant.USER_PASSWORD;
import static com.epam.shchehlov.constant.Constant.USER_SERVICE;

@WebServlet("/userRegistration")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, maxFileSize = 1024 * 1024 * 10)
public class UserRegistrationServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserRegistrationServlet.class);
    private UserService userService;
    private CaptchaService captchaService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(USER_SERVICE);
        captchaService = (CaptchaService) config.getServletContext().getAttribute(CAPTCHA_STRATEGY);
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("start doGet method");
        captchaService.setValue(request, response);
        request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
        removeErrorMessages(request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("start doPost method");

        removeErrorMessages(request);
        logger.info("error messages removed");

        request.getSession().removeAttribute(INFO_MESSAGE);

        logger.debug(request.getParameter("user_captcha => " + USER_CAPTCHA));

        UserRegistrationBean userRegistrationBean = RegistrationHandler.getUserRegistrationBean(request);
        logger.debug("userRegistrationBean created => " + userRegistrationBean);

        Map<String, String> errors = RegistrationHandler.validateUserBean(userRegistrationBean);

        if (!CaptchaHandler.validateCaptcha(captchaService, userRegistrationBean.getUserCaptcha(), request)) {
            logger.debug("captcha error");
            setTempValues(request, userRegistrationBean);
            setErrorMessages(request, errors);
            response.sendRedirect(PAGE_NAME_REGISTRATION);

        } else if (!errors.isEmpty()) {
            logger.debug("input has errors");

            setTempValues(request, userRegistrationBean);
            setErrorMessages(request, errors);
            request.getSession().setAttribute(ERROR_MESSAGE, MESSAGE_INVALID_INPUT);

            response.sendRedirect(PAGE_NAME_REGISTRATION);

        } else if (userService.isExistUser(userRegistrationBean.getLogin())) {
            logger.debug("login already exists");

            setTempValues(request, userRegistrationBean);
            request.getSession().setAttribute(ERROR_LOGIN, MESSAGE_LOGIN_EXISTS);
            request.getSession().setAttribute(ERROR_MESSAGE, MESSAGE_INVALID_INPUT);

            response.sendRedirect(PAGE_NAME_REGISTRATION);

        } else {
            AvatarHandler.saveAvatar(userRegistrationBean.getLogin(), request);

            userService.createUser(userRegistrationBean.createUser());
            logger.info(String.format("Created user %s", userService.getUser(userRegistrationBean.getLogin())));

            removeTempValues(request);
            removeErrorMessages(request);
            request.getSession().setAttribute(INFO_MESSAGE, String.format(MESSAGE_LOGIN_EXISTS_FORMAT, userRegistrationBean.getLogin()));
            logger.debug(INFO_MESSAGE + " => " + String.format(MESSAGE_LOGIN_EXISTS_FORMAT, userRegistrationBean.getLogin()));
            response.sendRedirect(PAGE_NAME_HOME);
        }
    }

    /**
     * Saves the field values entered by the user into session attributes.
     *
     * @param request              HttpServletRequest.
     * @param userRegistrationBean an instance of the class that stores the values entered by the user.
     */
    private void setTempValues(HttpServletRequest request, UserRegistrationBean userRegistrationBean) {
        request.getSession().setAttribute(TEMP_FIRST_NAME, userRegistrationBean.getFirstName());
        request.getSession().setAttribute(TEMP_LAST_NAME, userRegistrationBean.getLastName());
        request.getSession().setAttribute(TEMP_EMAIL, userRegistrationBean.getEmail());
        request.getSession().setAttribute(TEMP_LOGIN, userRegistrationBean.getLogin());
    }

    /**
     * Saves invalid data entered by the user during registration to session attributes.
     *
     * @param request HttpServletRequest.
     * @param errors  Map of errors.
     */
    private void setErrorMessages(HttpServletRequest request, Map<String, String> errors) {
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            if (entry.getKey().equals(USER_FIRST_NAME)) {
                request.getSession().setAttribute(ERROR_FIRST_NAME, entry.getValue());
            }
            if (entry.getKey().equals(USER_LAST_NAME)) {
                request.getSession().setAttribute(ERROR_LAST_NAME, entry.getValue());
            }
            if (entry.getKey().equals(USER_EMAIL)) {
                request.getSession().setAttribute(ERROR_EMAIL, entry.getValue());
            }
            if (entry.getKey().equals(USER_LOGIN)) {
                request.getSession().setAttribute(ERROR_LOGIN, entry.getValue());
            }
            if (entry.getKey().equals(USER_PASSWORD)) {
                request.getSession().setAttribute(ERROR_PASSWORD, entry.getValue());
            }
        }
    }

    /**
     * Removes from the session attributes fields values entered by the user.
     *
     * @param request HttpServletRequest.
     */
    private void removeTempValues(HttpServletRequest request) {
        request.getSession().removeAttribute(TEMP_FIRST_NAME);
        request.getSession().removeAttribute(TEMP_LAST_NAME);
        request.getSession().removeAttribute(TEMP_EMAIL);
        request.getSession().removeAttribute(TEMP_LOGIN);
    }

    /**
     * Removes from the session attributes invalid values of user input.
     *
     * @param request HttpServletRequest.
     */
    private void removeErrorMessages(HttpServletRequest request) {
        request.getSession().removeAttribute(ERROR_FIRST_NAME);
        request.getSession().removeAttribute(ERROR_LAST_NAME);
        request.getSession().removeAttribute(ERROR_EMAIL);
        request.getSession().removeAttribute(ERROR_LOGIN);
        request.getSession().removeAttribute(ERROR_PASSWORD);
        request.getSession().removeAttribute(ERROR_MESSAGE);
    }
}
