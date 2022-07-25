package com.epam.lab.shchehlov.task_04.command.impl;

import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.service.OrderService;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.util.OrdersPrinter;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.TreeMap;

/**
 * Class for displaying information about all orders made
 *
 * @author B.Shchehlov
 */
public class ShowAllOrdersCommand implements Command {
    private static final String COMMAND_NAME = "Show all orders";

    private final OrderService orderService;
    private final ProductService productService;

    public ShowAllOrdersCommand(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    public void execute() {
        TreeMap<LocalDateTime, Hashtable<Long, Integer>> orders = orderService.getAll();
        OrdersPrinter.printOrders(productService, orders);
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }
}
