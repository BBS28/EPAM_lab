package com.epam.shchehlov.filter;

import com.epam.shchehlov.entity.attribute.Role;
import com.epam.shchehlov.security.AccessService;
import com.epam.shchehlov.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.shchehlov.constant.Constant.ACCESS_MAP;
import static com.epam.shchehlov.constant.Constant.CURRENT_USER;
import static com.epam.shchehlov.constant.Constant.ERROR_403_PAGE;
import static com.epam.shchehlov.constant.Constant.FILTER_DESTROY;
import static com.epam.shchehlov.constant.Constant.FILTER_INIT;
import static com.epam.shchehlov.constant.Constant.LOGIN_PAGE;
import static com.epam.shchehlov.constant.Constant.USER_SERVICE;

public class AccessFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info(FILTER_INIT);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String userLogin = (String) session.getAttribute(CURRENT_USER);
        String url = String.valueOf(request.getRequestURL());

        AccessService accessService = (AccessService) request.getServletContext().getAttribute(ACCESS_MAP);

        if (accessService.isLimitedAccess(url)) {
            logger.debug("limited access page");

            if (userLogin == null) {
                logger.debug("User not logged in");
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                return;
            }

            UserService userService = (UserService) request.getServletContext().getAttribute(USER_SERVICE);
            Role role = userService.getUser(userLogin).getRole();
            if (!accessService.isUrlAccessible(role, url)) {
                logger.debug("Forbidden access");

                request.getRequestDispatcher(ERROR_403_PAGE).forward(request, response);
            }
        }
        logger.debug("Access allowed");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info(FILTER_DESTROY);
    }
}
