package com.epam.shchehlov.capatcha.factory;

import com.epam.shchehlov.capatcha.CaptchaService;
import com.epam.shchehlov.capatcha.impl.CookieCaptchaServiceImpl;
import com.epam.shchehlov.capatcha.impl.HiddenFieldCaptchaServiceImpl;
import com.epam.shchehlov.capatcha.impl.SessionCaptchaServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.epam.shchehlov.constant.Constant.CAPTCHA_TYPE;
import static com.epam.shchehlov.constant.Constant.COOKIE_CONST;
import static com.epam.shchehlov.constant.Constant.FIELD_CONST;
import static com.epam.shchehlov.constant.Constant.SESSION;

/**
 * Class for creating captcha service.
 *
 * @author B.Shchehlov.
 */
public class CaptchaServiceFactory {

    private static final Logger logger = Logger.getLogger(CaptchaServiceFactory.class);
    private static final Map<String, CaptchaService> captchaMap;

    static {
        captchaMap = new ConcurrentHashMap<>();
        captchaMap.put(SESSION, new SessionCaptchaServiceImpl());
        captchaMap.put(COOKIE_CONST, new CookieCaptchaServiceImpl());
        captchaMap.put(FIELD_CONST, new HiddenFieldCaptchaServiceImpl());
    }

    /**
     * Returns the implementation of the captcha service depending on the context.
     *
     * @param context ServletContext.
     * @return implementation of the captcha service depending on the context.
     */
    public static CaptchaService getCaptcha(ServletContext context) {
        String captchaServiceType = context.getInitParameter(CAPTCHA_TYPE);
        logger.debug("captchaServiceType => " + captchaServiceType);
        return Optional.ofNullable(captchaMap.get(captchaServiceType)).orElse(captchaMap.get(SESSION));
    }
}
