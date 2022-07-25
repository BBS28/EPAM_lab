package com.epam.lab.shchehlov.task_04.command;

import com.epam.lab.shchehlov.task_04.command.impl.*;
import com.epam.lab.shchehlov.task_04.service.AddedProductsService;
import com.epam.lab.shchehlov.task_04.service.BasketService;
import com.epam.lab.shchehlov.task_04.service.OrderService;
import com.epam.lab.shchehlov.task_04.service.ProductService;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Container for storing commands with assigned access key
 *
 * @author B.Shchehlov
 */
public class CommandContainer {
    private static final int DEFAULT_COMMAND_NUMBER = -1;

    private final Map<Integer, Command> commands;
    int commandNumber;

    public CommandContainer() {
        this.commands = new LinkedHashMap<>();
    }

    public CommandContainer(ProductService productService,
                            BasketService basketService,
                            OrderService orderService,
                            AddedProductsService addedProductsService,
                            Scanner scanner) {
        commandNumber = DEFAULT_COMMAND_NUMBER;
        commands = new LinkedHashMap<>();
        commands.put(commandNumber++, new NoCommand());
        commands.put(commandNumber++, new ExitCommand(productService));
        commands.put(commandNumber++, new ShowProductsCommand(productService));
        commands.put(commandNumber++, new AddToBasketCommand(productService, basketService, addedProductsService, scanner));
        commands.put(commandNumber++, new ShowBasketCommand(basketService, productService));
        commands.put(commandNumber++, new ChangeQuantity(basketService, scanner));
        commands.put(commandNumber++, new CreateOrderCommand(basketService, orderService, productService, scanner));
        commands.put(commandNumber++, new ShowLastAddedProductsCommand(addedProductsService));
        commands.put(commandNumber++, new ShowAllOrdersCommand(orderService, productService));
        commands.put(commandNumber++, new ShowOrdersInTimeLimitCommand(orderService, productService, scanner));
        commands.put(commandNumber++, new ShowClosestToDateOrderCommand(orderService, productService, scanner));
    }

    /**
     * Returns the selected command from the list by key
     *
     * @return the selected command
     */
    public Command getCommand(int i) {
        if (!commands.containsKey(i)) {
            return commands.get(DEFAULT_COMMAND_NUMBER);
        }
        return commands.get(i);
    }

    /**
     * Adds new command to CommandContainer
     */
    public void addCommand(Command command) {
        if (commandNumber == DEFAULT_COMMAND_NUMBER) {
            commandNumber++;
        }
        commands.put(commandNumber++, command);
    }

    public void addCommand(int key, Command command) {
        if (commands.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        commands.put(key, command);
    }

    /**
     * Returns all commands added to the container
     *
     * @return Map of all commands
     */
    public Map<Integer, Command> getCommands() {
        return Collections.unmodifiableMap(commands);
    }
}
