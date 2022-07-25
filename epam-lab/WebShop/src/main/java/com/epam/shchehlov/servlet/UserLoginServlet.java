package com.epam.shchehlov.servlet;

import com.epam.shchehlov.entity.User;
import com.epam.shchehlov.service.UserService;
import com.epam.shchehlov.util.LoginHandler;
import com.epam.shchehlov.util.security.PasswordSecurity;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.shchehlov.constant.Constant.CURRENT_USER;
import static com.epam.shchehlov.constant.Constant.LOGIN_PAGE;
import static com.epam.shchehlov.constant.Constant.LOG_IN_ERROR_MESSAGE;
import static com.epam.shchehlov.constant.Constant.MESSAGE_LOGIN_WRONG;
import static com.epam.shchehlov.constant.Constant.METHOD_GET_STARTED;
import static com.epam.shchehlov.constant.Constant.METHOD_POST_STARTED;
import static com.epam.shchehlov.constant.Constant.REFERER;
import static com.epam.shchehlov.constant.Constant.USER_LOGIN;
import static com.epam.shchehlov.constant.Constant.USER_PASSWORD;
import static com.epam.shchehlov.constant.Constant.USER_SERVICE;

@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserLoginServlet.class);

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute(USER_SERVICE);
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info(METHOD_GET_STARTED);
        request.getRequestDispatcher(request.getHeader(REFERER)).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info(METHOD_POST_STARTED);
        request.getSession().removeAttribute(LOG_IN_ERROR_MESSAGE);
        if (!LoginHandler.validateEmptyLogin(request)) {
            logger.info("login empty");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }

        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);

        if (userService.isExistUser(login)) {
            User user = userService.getUser(login);
            if (PasswordSecurity.isPasswordCorrect(password, user)) {
                request.getSession().setAttribute(CURRENT_USER, login);
                logger.info("Login user ==> " + user);
            } else {
                logger.info("Wrong password entered for login " + login);
                request.getSession().setAttribute(LOG_IN_ERROR_MESSAGE, MESSAGE_LOGIN_WRONG);
            }
        } else {
            logger.info("Login doesn't exist");
            request.getSession().setAttribute(LOG_IN_ERROR_MESSAGE, MESSAGE_LOGIN_WRONG);
        }

        response.sendRedirect(request.getHeader(REFERER));
    }
}
