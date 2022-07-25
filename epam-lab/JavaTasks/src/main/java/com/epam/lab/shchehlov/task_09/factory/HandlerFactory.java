package com.epam.lab.shchehlov.task_09.factory;

import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.container.ServerCommandContainer;
import com.epam.lab.shchehlov.task_09.handler.ServerHandler;

import java.net.Socket;

/**
 * Server handler creation interface.
 *
 * @author B.Shchehlov.
 */
public interface HandlerFactory {

    ServerHandler createServerHandler(Socket socket, ProductService productService, ServerCommandContainer commandContainer);
}
