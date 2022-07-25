package com.epam.shchehlov.capatcha.impl;

import com.epam.shchehlov.capatcha.CaptchaService;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import static com.epam.shchehlov.constant.Constant.CAPTCHA_ELEMENT_BOUND;
import static com.epam.shchehlov.constant.Constant.CAPTCHA_LENGTH;

/**
 * Abstract captcha service class.
 *
 * @author B.Shchehlov.
 */
public abstract class AbstractCaptchaServiceImpl implements CaptchaService {

    private final Random random = new Random();
    protected Map<Long, String> captchaMap = new ConcurrentHashMap<>();

    @Override
    public Map<Long, String> getCaptchaContainer() {
        return this.captchaMap;
    }

    /**
     * Returns captcha value.
     *
     * @return captcha value.
     */
    protected String generateCaptchaValue() {
        StringBuilder captchaValue = new StringBuilder();
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            captchaValue.append(random.nextInt(CAPTCHA_ELEMENT_BOUND));
        }
        return captchaValue.toString();
    }
}
