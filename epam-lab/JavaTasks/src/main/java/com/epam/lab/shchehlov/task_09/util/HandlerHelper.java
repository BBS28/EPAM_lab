package com.epam.lab.shchehlov.task_09.util;

import com.epam.lab.shchehlov.task_09.command.ServerCommand;
import com.epam.lab.shchehlov.task_09.command.container.ServerCommandContainer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.lab.shchehlov.task_09.constant.Constant.FIRST_INDEX;
import static com.epam.lab.shchehlov.task_09.constant.Constant.INVALID_COMMAND;
import static com.epam.lab.shchehlov.task_09.constant.Constant.LINE_CONNECTION;
import static com.epam.lab.shchehlov.task_09.constant.Constant.LINE_CONTENT_TYPE;
import static com.epam.lab.shchehlov.task_09.constant.Constant.LINE_HTTP;
import static com.epam.lab.shchehlov.task_09.constant.Constant.REGEX_EQUALS;
import static com.epam.lab.shchehlov.task_09.constant.Constant.REGEX_HTTP_COMMAND;
import static com.epam.lab.shchehlov.task_09.constant.Constant.REGEX_HTTP_DELIMITER;
import static com.epam.lab.shchehlov.task_09.constant.Constant.REGEX_TCP_COMMAND;
import static com.epam.lab.shchehlov.task_09.constant.Constant.SECOND_INDEX;

/**
 * Class containing methods for handling requests and creating responses.
 *
 * @author B.Shchehlov.
 */
public class HandlerHelper {

    private HandlerHelper() {
    }

    /**
     * Accepts the request, processes it with the appropriate command and returns the string value of the response result.
     *
     * @param request request from client.
     * @param serverCommandContainer container with server commands.
     * @param isTcp true if server TCP or false if HTTP.
     * @return the string value of the response result.
     */
    public static String getResult(String request, ServerCommandContainer serverCommandContainer, boolean isTcp) {
        Pattern pattern = Pattern.compile(isTcp ? REGEX_TCP_COMMAND : REGEX_HTTP_COMMAND);
        if (request == null) {
            return INVALID_COMMAND;
        } else {
            Matcher matcher = pattern.matcher(request);
            ServerCommand command;

            if (matcher.find()) {
                if (isTcp) {
                    command = serverCommandContainer.getServerCommand(request.split(REGEX_EQUALS)[FIRST_INDEX]);
                    return command.execute(request);
                }
                command = serverCommandContainer.getServerCommand(matcher.group(1).split(REGEX_HTTP_DELIMITER)[FIRST_INDEX]);
                return command.execute(matcher.group(SECOND_INDEX));
            } else {
                return INVALID_COMMAND;
            }
        }
    }

    /**
     * Returns HTTP response.
     *
     * @param responseStatus response status code.
     * @param result HTTP response result.
     * @return HTTP response.
     */
    public static String getHttpResponse(int responseStatus, String result) {
        return LINE_HTTP + responseStatus + System.lineSeparator() +
                LINE_CONTENT_TYPE + System.lineSeparator() +
                LINE_CONNECTION + System.lineSeparator() +
                System.lineSeparator() +
                result + System.lineSeparator();
    }
}
