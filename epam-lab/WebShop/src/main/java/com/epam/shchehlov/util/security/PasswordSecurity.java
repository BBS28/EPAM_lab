package com.epam.shchehlov.util.security;

import com.epam.shchehlov.entity.User;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.epam.shchehlov.constant.Constant.HASHING;
import static com.epam.shchehlov.constant.Constant.SALT;
import static com.epam.shchehlov.constant.Constant.SECURE_PASSWORD_ERROR;

/**
 * Class for securely storing a password in a database.
 *
 * @author B.Shchehlov.
 */
public class PasswordSecurity {

    private static final Logger logger = Logger.getLogger(PasswordSecurity.class);

    private PasswordSecurity() {
    }

    /**
     * Method to encrypt password using SHA algorithms with salt.
     *
     * @param passwordToHash not encrypted password
     * @return hashed password.
     */
    public static String getSecurePassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(HASHING);
            md.update(SALT.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            generatedPassword = bytesToHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            logger.error(SECURE_PASSWORD_ERROR + ExceptionUtils.getStackTrace(e));
        }
        return generatedPassword;
    }

    /**
     * Method for convert bytes array to hex code.
     *
     * @param bytes array of bytes which need to convert.
     * @return hex code.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    /**
     * Method check does password encrypted or not than check accordingly .
     *
     * @param providedPassword not encrypted password, user
     * @return String.
     */
    public static boolean isPasswordCorrect(String providedPassword, User user) {
        return user.getPassword().equals(getSecurePassword(providedPassword));
    }
}
