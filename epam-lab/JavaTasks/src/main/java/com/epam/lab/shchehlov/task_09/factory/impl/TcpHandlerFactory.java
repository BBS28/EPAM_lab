package com.epam.lab.shchehlov.task_09.factory.impl;

import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.container.ServerCommandContainer;
import com.epam.lab.shchehlov.task_09.factory.HandlerFactory;
import com.epam.lab.shchehlov.task_09.handler.ServerHandler;
import com.epam.lab.shchehlov.task_09.handler.impl.TcpHandler;

import java.net.Socket;

/**
 * Class for creating tcp server handler.
 *
 * @author B.Shchehlov.
 */
public class TcpHandlerFactory implements HandlerFactory {

    @Override
    public ServerHandler createServerHandler(Socket socket, ProductService productService, ServerCommandContainer commandContainer) {
        return new TcpHandler(productService, socket, commandContainer);
    }
}
