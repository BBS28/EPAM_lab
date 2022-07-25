package com.epam.lab.shchehlov.task_09.handler;

import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.container.ServerCommandContainer;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

import static com.epam.lab.shchehlov.task_09.constant.Constant.EXCEPTION_READING_DATA;

/**
 * Abstract class for server handlers.
 *
 * @author B.Shchehlov.
 */
public abstract class ServerHandler implements Runnable {
    private static final Logger log = Logger.getLogger(ServerHandler.class.getName());
    protected ProductService productService;
    protected Socket socket;
    protected ServerCommandContainer serverCommandContainer;

    protected ServerHandler(ProductService productService, Socket socket, ServerCommandContainer serverCommandContainer) {
        this.productService = productService;
        this.socket = socket;
        this.serverCommandContainer = serverCommandContainer;
    }

    protected void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            log.error(EXCEPTION_READING_DATA + ExceptionUtils.getStackTrace(e));
        }
    }
}
