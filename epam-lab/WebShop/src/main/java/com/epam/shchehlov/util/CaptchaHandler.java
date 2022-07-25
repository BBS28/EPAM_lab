package com.epam.shchehlov.util;

import com.epam.shchehlov.capatcha.CaptchaService;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Random;

import static com.epam.shchehlov.constant.Constant.CAPTCHA_CIRCLE_NUMBERS;
import static com.epam.shchehlov.constant.Constant.CAPTCHA_IMAGE_HEIGHT;
import static com.epam.shchehlov.constant.Constant.CAPTCHA_IMAGE_WIDTH;
import static com.epam.shchehlov.constant.Constant.CAPTCHA_TIME_IS_UP;
import static com.epam.shchehlov.constant.Constant.ERROR_MESSAGE;
import static com.epam.shchehlov.constant.Constant.INVALID_CAPTCHA;

/**
 * Class for handling captcha.
 *
 * @author B.Shchehlov.
 */
public class CaptchaHandler {

    private static final Logger logger = Logger.getLogger(CaptchaHandler.class);

    private CaptchaHandler() {
    }

    /**
     * Returns true if user entered valid value of captcha.
     *
     * @param captchaService CaptchaService.
     * @param captcha        value entered by user.
     * @param request        HttpServletRequest.
     * @return true if user entered valid value of captcha.
     */
    public static boolean validateCaptcha(CaptchaService captchaService, String captcha, HttpServletRequest request) {
        Map<Long, String> captchaContainer = captchaService.getCaptchaContainer();
        for (Map.Entry<Long, String> entry : captchaContainer.entrySet()) {
            logger.debug("captcha Time creation => " + entry.getKey() + " value => " + entry.getValue());
        }

        logger.debug("captchaService.getCreatedTime(request) => " + captchaService.getCreatedTime(request));

        if (captchaContainer.containsKey(captchaService.getCreatedTime(request))) {
            logger.debug("Time created captcha => " + captchaService.getCreatedTime(request));
            if (!captcha.equals(captchaContainer.get(captchaService.getCreatedTime(request)))) {
                request.getSession().setAttribute(ERROR_MESSAGE, INVALID_CAPTCHA);
                return false;
            }
        } else if (!captcha.equals(captchaContainer.get(captchaService.getCreatedTime(request)))) {
            request.getSession().setAttribute(ERROR_MESSAGE, CAPTCHA_TIME_IS_UP);
            return false;
        }
        return true;
    }

    /**
     * Generates a PNG image of captcha value with light gray background and yellow circles.
     *
     * @param text expects string size eight (8) digits.
     * @return byte array that is a PNG image generated with digits displayed.
     */
    public static byte[] drawCaptcha(String text) {
        int width = CAPTCHA_IMAGE_WIDTH;
        int height = CAPTCHA_IMAGE_HEIGHT;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D imageGraphics = image.createGraphics();
        imageGraphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        imageGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        imageGraphics.setColor(Color.LIGHT_GRAY);
        imageGraphics.fillRect(0, 0, width, height);
        imageGraphics.setFont(new Font("Serif", Font.PLAIN, 25));
        imageGraphics.setColor(Color.blue);
        int start = 10;
        byte[] bytes = text.getBytes();

        Random random = new Random();
        for (int i = 0; i < bytes.length; i++) {
            imageGraphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            imageGraphics.drawString(new String(new byte[]{bytes[i]}), start + (i * 25), (int) (Math.random() * 25 + 25));
        }
        imageGraphics.setColor(Color.YELLOW);
        for (int i = 0; i < CAPTCHA_CIRCLE_NUMBERS; i++) {
            imageGraphics.drawOval((int) (Math.random() * 160), (int) (Math.random() * 10), 50, 50);
        }
        imageGraphics.dispose();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", byteArrayOutputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
