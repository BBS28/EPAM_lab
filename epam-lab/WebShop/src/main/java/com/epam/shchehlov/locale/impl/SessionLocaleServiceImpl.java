package com.epam.shchehlov.locale.impl;

import com.epam.shchehlov.locale.LocaleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

import static com.epam.shchehlov.constant.Constant.LOCALE;

public class SessionLocaleServiceImpl implements LocaleService {

    @Override
    public Optional<Locale> getLocale(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute(LOCALE);
        return Optional.ofNullable(locale).map(Locale::new);
    }

    @Override
    public void setLocale(Locale locale, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(LOCALE, locale.getLanguage());
        response.setLocale(locale);
    }
}
