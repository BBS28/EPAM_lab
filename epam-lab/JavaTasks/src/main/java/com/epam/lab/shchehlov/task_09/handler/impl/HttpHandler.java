package com.epam.lab.shchehlov.task_09.handler.impl;

import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.container.ServerCommandContainer;
import com.epam.lab.shchehlov.task_09.handler.ServerHandler;
import com.epam.lab.shchehlov.task_09.util.HandlerHelper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static com.epam.lab.shchehlov.task_09.constant.Constant.EXCEPTION_READING_DATA;
import static com.epam.lab.shchehlov.task_09.constant.Constant.FORMAT_REQUEST_RECEIVED;
import static com.epam.lab.shchehlov.task_09.constant.Constant.FORMAT_RESPONSE_SENT;
import static com.epam.lab.shchehlov.task_09.constant.Constant.INVALID_COMMAND;
import static com.epam.lab.shchehlov.task_09.constant.Constant.STATUS_BAD_REQUEST;
import static com.epam.lab.shchehlov.task_09.constant.Constant.STATUS_SUCCESS;

/**
 * Class that handles client requests for HTTP Server.
 *
 * @author B.Shchehlov.
 */
public class HttpHandler extends ServerHandler {
    private static final Logger log = Logger.getLogger(HttpHandler.class.getName());
    private int status;

    public HttpHandler(ProductService productService, Socket socket, ServerCommandContainer serverCommandContainer) {
        super(productService, socket, serverCommandContainer);
        status = STATUS_SUCCESS;
    }

    /**
     * Reads a client request and writes a response.
     */
    @Override
    public void run() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String request = bufferedReader.readLine();
            log.info(String.format(FORMAT_REQUEST_RECEIVED, request));

            String result = HandlerHelper.getResult(request, serverCommandContainer, false);
            if (INVALID_COMMAND.equals(result)) {
                status = STATUS_BAD_REQUEST;
            }

            bufferedWriter.write(HandlerHelper.getHttpResponse(status, result));
            log.info(String.format(FORMAT_RESPONSE_SENT, result));
            bufferedWriter.flush();
            socket.close();
        } catch (IOException e) {
            log.error(EXCEPTION_READING_DATA + ExceptionUtils.getStackTrace(e));
        } finally {
            disconnect();
        }
    }
}
