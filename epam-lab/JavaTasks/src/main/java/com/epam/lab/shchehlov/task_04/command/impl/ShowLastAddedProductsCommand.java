package com.epam.lab.shchehlov.task_04.command.impl;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.service.AddedProductsService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class for displaying products added to basket at all sessions
 *
 * @author B.Shchehlov
 */
public class ShowLastAddedProductsCommand implements Command {
    private static final String COMMAND_NAME = "Show last added products";
    private static final String NO_PRODUCTS = "There is no added products at all yet :(. Please choose one!";

    private final AddedProductsService addedProductsService;

    public ShowLastAddedProductsCommand(AddedProductsService addedProductsService) {
        this.addedProductsService = addedProductsService;
    }

    @Override
    public void execute() {
        LinkedHashMap<Long, PowerTool> productList = addedProductsService.getAll();
        if (productList.isEmpty()) {
            System.out.println(NO_PRODUCTS);
        }
        for (Map.Entry<Long, PowerTool> entry : productList.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }
}
