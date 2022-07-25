package com.epam.shchehlov.filter;

import com.epam.shchehlov.filter.wrapper.LocaleWrapper;
import com.epam.shchehlov.locale.LocaleService;
import com.epam.shchehlov.locale.impl.CookieLocaleServiceImpl;
import com.epam.shchehlov.locale.impl.SessionLocaleServiceImpl;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.epam.shchehlov.constant.Constant.COOKIE;
import static com.epam.shchehlov.constant.Constant.COOKIE_TIME;
import static com.epam.shchehlov.constant.Constant.DATA_LOCALE;
import static com.epam.shchehlov.constant.Constant.DEFAULT_LOCALE;
import static com.epam.shchehlov.constant.Constant.FILTER_DESTROY;
import static com.epam.shchehlov.constant.Constant.FILTER_INIT;
import static com.epam.shchehlov.constant.Constant.PARAMETER_LOCALE;
import static com.epam.shchehlov.constant.Constant.STORAGE_LOCALE;

public class LocaleFilter implements Filter {

    private static final Logger logger = Logger.getLogger(LocaleFilter.class);
    private LocaleService localeService;
    private List<String> dataLocale;
    private Locale defaultLocale;

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info(FILTER_INIT);
        defaultLocale = new Locale(filterConfig.getInitParameter(DEFAULT_LOCALE));
        logger.debug("defaultLocale ==> " + defaultLocale);
        dataLocale = Arrays.asList(filterConfig.getInitParameter(DATA_LOCALE).split("\\s,"));
        logger.debug("dataLocale ==> " + dataLocale);
        String storageLocale = filterConfig.getInitParameter(STORAGE_LOCALE);
        logger.debug("storageLocale ==> " + storageLocale);

        if (COOKIE.equals(storageLocale)) {
            int cookieTime = Integer.parseInt(filterConfig.getInitParameter(COOKIE_TIME));
            localeService = new CookieLocaleServiceImpl(cookieTime);
        } else {
            localeService = new SessionLocaleServiceImpl();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("filter doFilter started");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Locale locale = getLocale(request);

        LocaleWrapper localeWrapper = getLocaleWrapper(locale, request, response);
        filterChain.doFilter(localeWrapper, response);
    }

    @Override
    public void destroy() {
        logger.info(FILTER_DESTROY);
    }

    private Locale getLocale(HttpServletRequest request) {
        return getRequestLocale(request)
                .orElse(localeService.getLocale(request)
                        .orElse(getBrowserLocale(request)
                                .orElse(defaultLocale)));
    }

    private Optional<Locale> getRequestLocale(HttpServletRequest request) {
        String localeName = request.getParameter(PARAMETER_LOCALE);
        return Optional.ofNullable(localeName).map(Locale::new);
    }

    private Optional<Locale> getBrowserLocale(HttpServletRequest request) {
        Enumeration<Locale> localeEnumeration = request.getLocales();
        List<Locale> locales = Collections.list(localeEnumeration);

        return locales.stream().filter(locale -> dataLocale.contains(locale.getLanguage())).findFirst();
    }

    private LocaleWrapper getLocaleWrapper(Locale locale, HttpServletRequest request, HttpServletResponse response) {
        localeService.setLocale(locale, request, response);

        return new LocaleWrapper(request, locale.getLanguage());
    }
}
