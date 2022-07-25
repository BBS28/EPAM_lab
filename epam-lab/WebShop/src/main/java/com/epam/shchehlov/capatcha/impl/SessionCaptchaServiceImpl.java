package com.epam.shchehlov.capatcha.impl;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.shchehlov.constant.Constant.CAPTCHA_CREATED_TIME;
import static com.epam.shchehlov.constant.Constant.CAPTCHA_VALUE;
import static java.lang.System.currentTimeMillis;

public class SessionCaptchaServiceImpl extends AbstractCaptchaServiceImpl {

    private static final Logger logger = Logger.getLogger(SessionCaptchaServiceImpl.class);

    @Override
    public void setValue(HttpServletRequest request, HttpServletResponse response) {
        long timeGenerated = currentTimeMillis();
        String captchaValue = generateCaptchaValue();
        captchaMap.put(timeGenerated, captchaValue);
        request.getSession().setAttribute(CAPTCHA_VALUE, captchaValue);
        request.getSession().setAttribute(CAPTCHA_CREATED_TIME, timeGenerated);
        logger.info("set captcha value => " + captchaValue);
    }

    @Override
    public String getValue(HttpServletRequest request) {
        return request.getSession().getAttribute(CAPTCHA_VALUE).toString();
    }

    @Override
    public long getCreatedTime(HttpServletRequest request) {
        return Long.parseLong(request.getSession().getAttribute(CAPTCHA_CREATED_TIME).toString());
    }
}
