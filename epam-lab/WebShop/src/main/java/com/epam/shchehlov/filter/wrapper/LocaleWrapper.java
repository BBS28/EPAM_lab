package com.epam.shchehlov.filter.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class LocaleWrapper extends HttpServletRequestWrapper {

    private final String locale;

    public LocaleWrapper(HttpServletRequest request, String locale) {
        super(request);
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return new Locale(locale);
    }

    @Override
    public Enumeration<Locale> getLocales() {
        List<Locale> localeList = new ArrayList<>();
        localeList.add(new Locale(locale));
        return Collections.enumeration(localeList);
    }
}
