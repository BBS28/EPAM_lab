package com.epam.shchehlov.servlet;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.shchehlov.constant.Constant.CURRENT_USER;
import static com.epam.shchehlov.constant.Constant.REFERER;

@WebServlet("/userLogout")
public class UserLogoutServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserLogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute(CURRENT_USER);
        response.sendRedirect(request.getHeader(REFERER));
        logger.info(CURRENT_USER + "logged out");
    }
}
