package com.epam.shchehlov.capatcha;

import org.apache.log4j.Logger;

import java.util.Map;

import static java.lang.System.currentTimeMillis;

/**
 * Class that controls captcha lifetime.
 *
 * @author B.Shchehlov.
 */
public class CaptchaController implements Runnable {

    private static final Logger logger = Logger.getLogger(CaptchaController.class);
    private final Map<Long, String> captchaMap;
    private final long captchaTime;

    public CaptchaController(CaptchaService captchaService, long captchaTime) {
        this.captchaMap = captchaService.getCaptchaContainer();
        this.captchaTime = captchaTime;
    }

    @Override
    public void run() {
        long currentTime = currentTimeMillis();
        for (Map.Entry<Long, String> e : captchaMap.entrySet()) {
            long timer = currentTime - e.getKey();
            if (timer >= captchaTime) {
                String captcha = e.getValue();
                captchaMap.remove(e.getKey());
                logger.info(String.format("Captcha %s removed!", captcha));
            }
        }
    }
}
