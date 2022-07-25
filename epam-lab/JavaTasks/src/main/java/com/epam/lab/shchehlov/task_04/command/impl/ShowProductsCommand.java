package com.epam.lab.shchehlov.task_04.command.impl;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.service.ProductService;

import java.util.Map;

/**
 * Class for displaying all products available to order
 *
 * @author B.Shchehlov
 */
public class ShowProductsCommand implements Command {
    private static final String COMMAND_NAME = "Show all products";

    private final ProductService productService;

    public ShowProductsCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        for (Map.Entry<Long, PowerTool> entry : productService.getAll().entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }
}
