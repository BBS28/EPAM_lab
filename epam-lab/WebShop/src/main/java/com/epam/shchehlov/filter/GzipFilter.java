package com.epam.shchehlov.filter;

import com.epam.shchehlov.filter.wrapper.GzipResponseWrapper;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.shchehlov.constant.Constant.ACCEPT_ENCODING;
import static com.epam.shchehlov.constant.Constant.CONTENT_ENCODING;
import static com.epam.shchehlov.constant.Constant.FILTER_DESTROY;
import static com.epam.shchehlov.constant.Constant.FILTER_INIT;
import static com.epam.shchehlov.constant.Constant.GZIP;
import static com.epam.shchehlov.constant.Constant.PATH_CAPTCHA;
import static com.epam.shchehlov.constant.Constant.REGEX_IMAGE;

public class GzipFilter implements Filter {

    private static final Logger logger = Logger.getLogger(GzipFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info(FILTER_INIT);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (checkAcceptEncoding(request) && checkForTextContent(request)) {
            response.addHeader(CONTENT_ENCODING, GZIP);
            GzipResponseWrapper responseWrapper = new GzipResponseWrapper(response);

            filterChain.doFilter(request, responseWrapper);
            responseWrapper.closeWriter();
            logger.info("filter has done the compression");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean checkAcceptEncoding(HttpServletRequest request) {
        String acceptEncoding = request.getHeader(ACCEPT_ENCODING);
        return acceptEncoding != null && acceptEncoding.contains(GZIP);
    }

    private boolean checkForTextContent(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        return !requestURI.matches(REGEX_IMAGE) && !requestURI.contains(PATH_CAPTCHA);
    }

    @Override
    public void destroy() {
        logger.info(FILTER_DESTROY);
    }
}
