package com.epam.shchehlov.locale.impl;

import com.epam.shchehlov.locale.LocaleService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import static com.epam.shchehlov.constant.Constant.LOCALE;

public class CookieLocaleServiceImpl implements LocaleService {

    private final int cookieTime;

    public CookieLocaleServiceImpl(int cookieTime) {
        this.cookieTime = cookieTime;
    }

    @Override
    public Optional<Locale> getLocale(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(LOCALE))
                .findFirst()
                .map(cookie -> new Locale(cookie.getValue()));
    }

    @Override
    public void setLocale(Locale locale, HttpServletRequest request, HttpServletResponse response) {
        removeCookie(response);
        Cookie cookie = new Cookie(LOCALE, locale.getLanguage());
        cookie.setMaxAge(cookieTime);
        response.addCookie(cookie);
        response.setLocale(locale);
    }

    private void removeCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(LOCALE, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
