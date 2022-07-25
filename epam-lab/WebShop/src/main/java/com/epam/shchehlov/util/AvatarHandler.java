package com.epam.shchehlov.util;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.epam.shchehlov.constant.Constant.AVATAR_DIRECTORY;
import static com.epam.shchehlov.constant.Constant.DELIMITER_DOT;
import static com.epam.shchehlov.constant.Constant.IMAGE_EXTENSION;
import static com.epam.shchehlov.constant.Constant.MESSAGE_CANT_SAVE_AVATAR;
import static com.epam.shchehlov.constant.Constant.USER_AVATAR;

/**
 * Class for handling user avatar.
 *
 * @author B.Shchehlov.
 */
public class AvatarHandler {

    private static final Logger logger = Logger.getLogger(AvatarHandler.class);

    private AvatarHandler() {
    }

    public static void saveAvatar(String login, HttpServletRequest request) throws ServletException, IOException {
        String avatarDirectoryPath = request.getServletContext().getAttribute(AVATAR_DIRECTORY).toString();
        logger.debug("avatarDirectoryPath ==> " + avatarDirectoryPath);
        String avatarPath = avatarDirectoryPath + login + DELIMITER_DOT + IMAGE_EXTENSION;
        logger.debug("part => " + request.getPart(USER_AVATAR));

        try (InputStream inputStream = request.getPart(USER_AVATAR).getInputStream()) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            if (bufferedImage != null) {
                ImageIO.write(bufferedImage, IMAGE_EXTENSION, new File(avatarPath));
            }
        } catch (IOException | ServletException e) {
            logger.info(MESSAGE_CANT_SAVE_AVATAR);
        }
    }
}
