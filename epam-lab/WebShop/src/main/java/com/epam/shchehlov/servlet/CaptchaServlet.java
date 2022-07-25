package com.epam.shchehlov.servlet;

import com.epam.shchehlov.capatcha.CaptchaService;
import com.epam.shchehlov.util.CaptchaHandler;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static com.epam.shchehlov.constant.Constant.CAPTCHA_STRATEGY;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CaptchaServlet.class);

    private CaptchaService captchaService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        captchaService = (CaptchaService) config.getServletContext().getAttribute(CAPTCHA_STRATEGY);
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (OutputStream outputStream = response.getOutputStream()) {
            String captcha = captchaService.getValue(request);
            logger.debug("captcha value => " + captcha);
            outputStream.write(CaptchaHandler.drawCaptcha(captcha));
        }
    }
}
