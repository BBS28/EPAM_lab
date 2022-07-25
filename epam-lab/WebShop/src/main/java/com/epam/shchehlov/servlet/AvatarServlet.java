package com.epam.shchehlov.servlet;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static com.epam.shchehlov.constant.Constant.AVATAR_DIRECTORY;
import static com.epam.shchehlov.constant.Constant.CURRENT_USER;
import static com.epam.shchehlov.constant.Constant.DEFAULT_AVATAR;
import static com.epam.shchehlov.constant.Constant.DELIMITER_DOT;
import static com.epam.shchehlov.constant.Constant.IMAGE_EXTENSION;

@WebServlet("/userAvatar")
public class AvatarServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AvatarServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String currentUser = (String) request.getSession().getAttribute(CURRENT_USER);
        String avatarDirectory = (String) request.getServletContext().getAttribute(AVATAR_DIRECTORY);
        File avatarFile = new File(avatarDirectory + currentUser + DELIMITER_DOT + IMAGE_EXTENSION);

        if (avatarFile.exists()) {
            logger.debug("avatar image exists");
            ImageIO.write(ImageIO.read(avatarFile), IMAGE_EXTENSION, response.getOutputStream());
        } else {
            logger.debug("avatar image isn't exist");
            File imageFile = new File(request.getServletContext().getRealPath(DEFAULT_AVATAR));
            ImageIO.write(ImageIO.read(imageFile), IMAGE_EXTENSION, response.getOutputStream());
        }
    }
}
