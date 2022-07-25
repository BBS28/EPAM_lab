package com.epam.lab.shchehlov.task_04.command.impl;

import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.service.OrderService;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.util.DateTimeValidator;
import com.epam.lab.shchehlov.task_04.util.OrdersPrinter;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Class for displaying orders in the interval between the given dates
 *
 * @author B.Shchehlov
 */
public class ShowOrdersInTimeLimitCommand implements Command {
    private static final String COMMAND_NAME = "Show orders for the selected period";
    private static final String ENTER_FIRST_DATE = "Enter first date";
    private static final String ENTER_SECOND_DATE = "Enter second date";
    private static final String WRONG_ENTER = "The first date must be earlier than the second\nPlease repeat enter";
    private static final String NO_ORDERS = "there are no orders during this period";
    private static final String LIST_ORDERS = "Orders for the selected period:";


    private final OrderService orderService;
    private final ProductService productService;
    private final Scanner scanner;

    public ShowOrdersInTimeLimitCommand(OrderService orderService, ProductService productService, Scanner scanner) {
        this.orderService = orderService;
        this.productService = productService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        TreeMap<LocalDateTime, Hashtable<Long, Integer>> orders = null;
        boolean isCorrectEnter = false;

        do {
            System.out.println(ENTER_FIRST_DATE);
            LocalDateTime date1 = DateTimeValidator.input(scanner);
            System.out.println(ENTER_SECOND_DATE);
            LocalDateTime date2 = DateTimeValidator.input(scanner);

            if (!date1.isBefore(date2)) {
                System.out.println(WRONG_ENTER);
            } else {
                orders = orderService.getOrdersForPeriod(date1, date2);
                isCorrectEnter = true;
            }
        } while (!isCorrectEnter);

        if (orders == null) {
            System.out.println(NO_ORDERS);
        } else {
            System.out.println(LIST_ORDERS);
            OrdersPrinter.printOrders(productService, orders);
        }
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }
}
