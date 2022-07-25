package com.epam.shchehlov.filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.shchehlov.constant.Constant.CACHE_CONTROL;
import static com.epam.shchehlov.constant.Constant.EXPIRES;
import static com.epam.shchehlov.constant.Constant.FILTER_DESTROY;
import static com.epam.shchehlov.constant.Constant.FILTER_INIT;
import static com.epam.shchehlov.constant.Constant.MAX_AGE_ZERO;
import static com.epam.shchehlov.constant.Constant.NO_CACHE;
import static com.epam.shchehlov.constant.Constant.NO_STORE;
import static com.epam.shchehlov.constant.Constant.PRAGMA;

public class CacheBreakerFilter implements Filter {

    private static final Logger logger = Logger.getLogger(CacheBreakerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info(FILTER_INIT);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader(CACHE_CONTROL, MAX_AGE_ZERO);
        response.setHeader(CACHE_CONTROL, NO_CACHE);
        response.setHeader(CACHE_CONTROL, NO_STORE);
        response.setDateHeader(EXPIRES, 0);
        response.setHeader(PRAGMA, NO_CACHE);

        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {
        logger.info(FILTER_DESTROY);
    }
}
