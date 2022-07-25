package com.epam.lab.shchehlov.task_04.command.impl;

import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.service.BasketService;
import com.epam.lab.shchehlov.task_04.service.OrderService;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.util.DateTimeValidator;
import com.epam.lab.shchehlov.task_04.util.OrdersPrinter;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Implementation of interface Command for creating order
 *
 * @author B.Shchehlov
 */
public class CreateOrderCommand implements Command {
    private static final String COMMAND_NAME = "Create an order";
    private static final String BASKET_EMPTY = "The basket is empty. Add at least one product to it.";
    private static final String ENTER_DATE = "Enter current date and time:";
    private static final String SUCCESS = "Congratulations, you've created the order! Your order is:";

    private final BasketService basketService;
    private final OrderService orderService;
    private final ProductService productService;
    private final Scanner scanner;

    public CreateOrderCommand(BasketService basketService, OrderService orderService, ProductService productService, Scanner scanner) {
        this.basketService = basketService;
        this.orderService = orderService;
        this.productService = productService;
        this.scanner = scanner;
    }

    /**
     * Creates order based on items added to the basket
     */
    @Override
    public void execute() {
        if (basketService.isEmpty()) {
            System.out.println(BASKET_EMPTY);
        }

        System.out.println(ENTER_DATE);
        Hashtable<Long, Integer> order = basketService.getAll();
        LocalDateTime date = DateTimeValidator.input(scanner);
        orderService.addOne(date);

        System.out.println(SUCCESS);
        OrdersPrinter.printOneOrder(productService, order);
        basketService.deleteAll();
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }
}
