package com.epam.lab.shchehlov.task_09.server;

import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.container.ServerCommandContainer;
import com.epam.lab.shchehlov.task_09.factory.HandlerFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.epam.lab.shchehlov.task_09.constant.Constant.EXCEPTION_READING_DATA;
import static com.epam.lab.shchehlov.task_09.constant.Constant.FORMAT_THREAD_STARTED;

/**
 * Abstract class for servers.
 *
 * @author B.Shchehlov.
 */
public abstract class Server extends Thread {
    private static final Logger log = Logger.getLogger(Server.class.getName());
    protected final ProductService productService;
    protected final ServerCommandContainer commandContainer;
    protected HandlerFactory handlerFactory;
    protected int port;

    protected Server(ProductService productService, ServerCommandContainer commandContainer, int port) {
        this.productService = productService;
        this.commandContainer = commandContainer;
        this.port = port;
    }

    /**
     * Creates a thread that handles clients requests.
     */
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info(String.format(FORMAT_THREAD_STARTED, this.getClass().getSimpleName()));
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(handlerFactory.createServerHandler(socket, productService, commandContainer)).start();
            }
        } catch (IOException e) {
            log.error(EXCEPTION_READING_DATA + ExceptionUtils.getStackTrace(e));
        }
    }
}
