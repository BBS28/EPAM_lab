package com.epam.lab.shchehlov.task_09.server.impl;

import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.container.ServerCommandContainer;
import com.epam.lab.shchehlov.task_09.factory.impl.HttpHandlerFactory;
import com.epam.lab.shchehlov.task_09.server.Server;

/**
 * HTTP server class.
 *
 * @author B.Shchehlov.
 */
public class HttpServer extends Server {

    public HttpServer(ProductService productService, ServerCommandContainer commandContainer, int port) {
        super(productService, commandContainer, port);
        this.handlerFactory = new HttpHandlerFactory();
    }
}
