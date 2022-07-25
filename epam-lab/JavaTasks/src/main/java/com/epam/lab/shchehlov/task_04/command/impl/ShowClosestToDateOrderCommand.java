package com.epam.lab.shchehlov.task_04.command.impl;

import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.service.OrderService;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.util.DateTimeValidator;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Scanner;

import static com.epam.lab.shchehlov.task_04.util.OrdersPrinter.printOneOrder;

/**
 * Class for displaying order closest to the selected date
 *
 * @author B.Shchehlov
 */
public class ShowClosestToDateOrderCommand implements Command {
    private static final String COMMAND_NAME = "Show the order closest to a given date";
    private static final String ENTER_DATE = "Enter the date";
    private static final String CLOSEST_ORDER = "Order closest to the selected date:";

    private final OrderService orderService;
    private final ProductService productService;
    private final Scanner scanner;

    public ShowClosestToDateOrderCommand(OrderService orderService, ProductService productService, Scanner scanner) {
        this.orderService = orderService;
        this.productService = productService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println(ENTER_DATE);
        LocalDateTime date = DateTimeValidator.input(scanner);
        Hashtable<Long, Integer> order = orderService.getByClosestDate(date);

        System.out.println(CLOSEST_ORDER);
        printOneOrder(productService, order);
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }
}
