package com.epam.shchehlov.capatcha.impl;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static com.epam.shchehlov.constant.Constant.HIDDEN_FIELD;
import static java.lang.System.currentTimeMillis;

public class HiddenFieldCaptchaServiceImpl extends AbstractCaptchaServiceImpl {

    private static final Logger logger = Logger.getLogger(HiddenFieldCaptchaServiceImpl.class);

    @Override
    public void setValue(HttpServletRequest request, HttpServletResponse response) {
        long timeGenerated = currentTimeMillis();
        String captchaValue = generateCaptchaValue();
        logger.debug(String.format("set captcha value => %s", captchaValue));
        captchaMap.put(timeGenerated, captchaValue);
        request.setAttribute(HIDDEN_FIELD, timeGenerated);
    }

    @Override
    public String getValue(HttpServletRequest request) {
        return captchaMap.get(getCreatedTime(request));
    }

    @Override
    public long getCreatedTime(HttpServletRequest request) {
        String timeValue = request.getParameter(HIDDEN_FIELD);
        if (Objects.isNull(timeValue)) {
            return Long.MIN_VALUE;
        }
        return Long.parseLong(timeValue);
    }
}
