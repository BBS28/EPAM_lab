package com.epam.lab.shchehlov.task_04.util;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.service.ProductService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

/**
 * A class that displays orders to the console
 *
 * @author B.Shchehlov
 */
public class OrdersPrinter {
    private static final String DATE = "Date: %s%n";
    private static final String PRODUCTS = "Products:";
    private static final String PRODUCT_QUANTITY = "%s - %d pcs%n";
    private static final String AMOUNT = "Amount: %d,00 UAH%n";
    private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final String DELIMITER = "--------------------------------------------------------";

    /**
     * Prints a list of orders indicating the date, products and the total amount
     */
    public static void printOrders(ProductService productService, TreeMap<LocalDateTime, Hashtable<Long, Integer>> orders) {
        for (Map.Entry<LocalDateTime, Hashtable<Long, Integer>> orderEntry : orders.entrySet()) {
            System.out.printf(DATE, orderEntry.getKey().format(DateTimeFormatter.ofPattern(FORMAT_DATE_TIME)));
            System.out.println(PRODUCTS);
            printOneOrder(productService, orderEntry.getValue());
            System.out.println(DELIMITER);
        }
    }

    /**
     * Prints one specific order indicating the goods and the total amount
     */
    public static void printOneOrder(ProductService productService, Hashtable<Long, Integer> order) {
        int amount = 0;

        for (Map.Entry<Long, Integer> basketEntry : order.entrySet()) {
            PowerTool tool = productService.getOne(basketEntry.getKey());
            int quantity = basketEntry.getValue();
            System.out.printf(PRODUCT_QUANTITY, tool, quantity);
            amount += tool.getPrice() * quantity;
        }

        System.out.printf(AMOUNT, amount);
    }
}
