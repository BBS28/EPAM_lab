package com.epam.lab.shchehlov.task_04.command.impl;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.service.BasketService;
import com.epam.lab.shchehlov.task_04.service.ProductService;

import java.util.Map;

/**
 * Class for displaying information about all products in the basket
 *
 * @author B.Shchehlov
 */
public class ShowBasketCommand implements Command {
    private static final String COMMAND_NAME = "Show basket";
    private static final String EMPTY_BASKET = "Basket is empty";
    private static final String VALUE_FORMAT = "Total value : %d,00 UAH%n";
    private static final String PRODUCT_FORMAT = "(id-%d) %s - %d pcs%n";

    private final BasketService basketService;
    private final ProductService productService;

    public ShowBasketCommand(BasketService basketService, ProductService productService) {
        this.basketService = basketService;
        this.productService = productService;
    }

    @Override
    public void execute() {
        if (basketService.getAll().isEmpty()) {
            System.out.println(EMPTY_BASKET);
        }
        int cost = 0;
        for (Map.Entry<Long, Integer> entry : basketService.getAll().entrySet()) {
            PowerTool powerTool = productService.getOne(entry.getKey());
            int quantity = entry.getValue();
            System.out.printf(PRODUCT_FORMAT, powerTool.getId(), powerTool.getName(), quantity);
            cost += powerTool.getPrice() * quantity;
        }
        System.out.printf(VALUE_FORMAT, cost);
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }
}
