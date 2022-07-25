package com.epam.lab.shchehlov.task_09.command.impl.tcp;

import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.ServerCommand;

/**
 * Handles execution of counting products when processed at tcp server.
 *
 * @author B.Shchehlov.
 */
public class GetCountTcpServerCommand implements ServerCommand {
    private final ProductService productService;

    public GetCountTcpServerCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(String request) {
        return String.valueOf(productService.getAll().size());
    }
}
