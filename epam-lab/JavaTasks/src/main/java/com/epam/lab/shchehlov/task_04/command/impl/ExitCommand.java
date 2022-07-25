package com.epam.lab.shchehlov.task_04.command.impl;

import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_06.constant.Constant;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_06.util.ProductSerializer;

/**
 * Ends application execution
 *
 * @author B.Shchehlov
 */
public class ExitCommand implements Command {
    private static final String COMMAND_NAME = "Leave the shop";
    private static final String FAREWELL_MESSAGE = "Bye :(";

    private final ProductService productService;

    public ExitCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        ProductSerializer.saveProducts(productService, Constant.PRODUCT_DATA_FILE);
        System.out.println(FAREWELL_MESSAGE);
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }
}
