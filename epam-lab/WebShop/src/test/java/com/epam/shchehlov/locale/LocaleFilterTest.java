package com.epam.shchehlov.locale;

import com.epam.shchehlov.filter.LocaleFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.epam.shchehlov.constant.Constant.LOCALE;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocaleFilterTest {

    private static final String LOCALE_EN = "en";
    private static final String LOCALE_RU = "ru";
    private static final String LOCALE_FR = "fr";
    private static final String LOCALE_UK = "uk";
    private static final String LANG = "lang";

    @InjectMocks
    private LocaleFilter localeFilter;

    @Mock
    private LocaleService mockLocaleService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Spy
    private FilterChain filterChain;

    @Mock
    private HttpSession session;

    @Spy
    private List<String> dataLocale;

    @Spy
    private Locale defaultLocale = new Locale(LOCALE_EN);

    @Test
    public void shouldGetLocaleFromSession() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        request.getSession().setAttribute(LOCALE, LOCALE_UK);
        when(mockLocaleService.getLocale(request)).thenReturn(Optional.of(new Locale(LOCALE_UK)));
        when(request.getLocales()).thenReturn(Collections.enumeration(new ArrayList<>()));

        localeFilter.doFilter(request, response, filterChain);

        verify(mockLocaleService).setLocale(eq(new Locale(LOCALE_UK)), eq(request), eq(response));
    }

    @Test
    public void shouldGetLocaleFromCookie() throws ServletException, IOException {
        when(request.getLocales()).thenReturn(Collections.enumeration(new ArrayList<>()));
        when(mockLocaleService.getLocale(request)).thenReturn(Optional.of(new Locale(LOCALE_UK)));

        localeFilter.doFilter(request, response, filterChain);

        verify(mockLocaleService).setLocale(eq(new Locale(LOCALE_UK)), eq(request), eq(response));
    }

    @Test
    public void shouldGetLocaleFromRequestParameter() throws ServletException, IOException {
        when(request.getParameter(LANG)).thenReturn(LOCALE_EN);
        when(request.getLocales()).thenReturn(Collections.enumeration(new ArrayList<>()));

        localeFilter.doFilter(request, response, filterChain);

        verify(mockLocaleService).setLocale(eq(new Locale(LOCALE_EN)), eq(request), eq(response));
    }

    @Test
    public void shouldGetLocaleFromBrowser() throws ServletException, IOException {
        when(mockLocaleService.getLocale(request)).thenReturn(Optional.empty());
        List<Locale> locales = new ArrayList<>();
        locales.add(new Locale(LOCALE_UK));
        locales.add(new Locale(LOCALE_FR));
        locales.add(new Locale(LOCALE_RU));
        when(request.getLocales()).thenReturn(Collections.enumeration(locales));
        when(dataLocale.contains(LOCALE_UK)).thenReturn(true);

        localeFilter.doFilter(request, response, filterChain);

        verify(mockLocaleService).setLocale(eq(new Locale(LOCALE_UK)), eq(request), eq(response));
    }

    @Test
    public void shouldGetDefaultLocaleWhenNoSuitable() throws ServletException, IOException {
        when(mockLocaleService.getLocale(request)).thenReturn(Optional.empty());
        when(request.getLocales()).thenReturn(Collections.enumeration(new ArrayList<>()));

        localeFilter.doFilter(request, response, filterChain);

        verify(mockLocaleService).setLocale(eq(new Locale(LOCALE_EN)), eq(request), eq(response));
    }
}
