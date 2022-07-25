package com.epam.lab.shchehlov.task_09.command.impl.http;

import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.ServerCommand;

import static com.epam.lab.shchehlov.task_09.constant.Constant.FORMAT_JSON_GET_COUNT;

/**
 * Handles execution of counting products when processed at http server.
 *
 * @author B.Shchehlov.
 */
public class GetCountHttpServerCommand implements ServerCommand {
    private final ProductService productService;

    public GetCountHttpServerCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(String request) {
        return String.format(FORMAT_JSON_GET_COUNT, productService.getAll().size());
    }
}
