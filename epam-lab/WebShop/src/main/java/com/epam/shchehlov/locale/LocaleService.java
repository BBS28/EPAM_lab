package com.epam.shchehlov.locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

public interface LocaleService {

    Optional<Locale> getLocale(HttpServletRequest servletRequest);

    void setLocale(Locale locale, HttpServletRequest request, HttpServletResponse response);
}

