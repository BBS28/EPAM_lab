package com.epam.shchehlov.capatcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Captcha interface.
 *
 * @author B.Shchehlov.
 */
public interface CaptchaService {

    /**
     * Returns a map with a key that stores the creation time and a value - the value of the captcha.
     *
     * @return Map of captcha values.
     */
    Map<Long, String> getCaptchaContainer();

    /**
     * Returns value of captcha.
     *
     * @param request HttpServletRequest.
     * @return value of captcha.
     */
    String getValue(HttpServletRequest request);

    /**
     * Returns time of captcha creation.
     *
     * @param request HttpServletRequest.
     * @return time of captcha creation.
     */
    long getCreatedTime(HttpServletRequest request);

    /**
     * Puts captcha value to captcha container.
     *
     * @param request  HttpServletRequest.
     * @param response HttpServletResponse.
     */
    void setValue(HttpServletRequest request, HttpServletResponse response);
}
