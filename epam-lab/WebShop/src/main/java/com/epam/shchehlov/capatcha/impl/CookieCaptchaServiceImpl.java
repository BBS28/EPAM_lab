package com.epam.shchehlov.capatcha.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.shchehlov.constant.Constant.CAPTCHA_CREATED_TIME;
import static java.lang.System.currentTimeMillis;

/**
 * Captcha service that stores the captcha value in a cookie.
 *
 * @author B.Shchehlov.
 */
public class CookieCaptchaServiceImpl extends AbstractCaptchaServiceImpl {

    @Override
    public void setValue(HttpServletRequest request, HttpServletResponse response) {
        long timeGenerated = currentTimeMillis();
        captchaMap.put(timeGenerated, generateCaptchaValue());
        response.addCookie(new Cookie(CAPTCHA_CREATED_TIME, Long.toString(timeGenerated)));
    }

    @Override
    public String getValue(HttpServletRequest request) {
        return captchaMap.get(getCreatedTime(request));
    }

    @Override
    public long getCreatedTime(HttpServletRequest request) {
        Cookie[] cookieList = request.getCookies();

        for (Cookie cookie : cookieList) {
            if (CAPTCHA_CREATED_TIME.equals(cookie.getName())) {
                return Long.parseLong(cookie.getValue());
            }
        }
        return Long.MIN_VALUE;
    }
}
