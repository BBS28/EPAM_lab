package com.epam.lab.shchehlov.task_09.server.impl;

import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.container.ServerCommandContainer;
import com.epam.lab.shchehlov.task_09.factory.impl.TcpHandlerFactory;
import com.epam.lab.shchehlov.task_09.server.Server;

/**
 * TCP server class.
 *
 * @author B.Shchehlov.
 */
public class TcpServer extends Server {

    public TcpServer(ProductService productService, ServerCommandContainer commandContainer, int port) {
        super(productService, commandContainer, port);
        this.handlerFactory = new TcpHandlerFactory();
    }
}
