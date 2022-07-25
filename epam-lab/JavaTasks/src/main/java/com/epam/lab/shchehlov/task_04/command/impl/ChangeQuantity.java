package com.epam.lab.shchehlov.task_04.command.impl;

import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.service.BasketService;

import java.util.Scanner;

/**
 * Implementation of interface Command for changing quantity of product in the basket
 *
 * @author B.Shchehlov
 */
public class ChangeQuantity implements Command {
    private static final String COMMAND_NAME = "Change quantity of product in the basket";
    private static final String CHOOSE_ID = "Choose ID of product in the basket: ";
    private static final String ENTER_QUANTITY = "Enter quantity:";
    private static final String SUCCESS_CHANGING = "Quantity changed!";
    private static final String WRONG_ID = "Product with this id does not contain in the basket!\nRepeat enter please";
    private static final String NOT_NUMERICAL = "Please enter a numerical value!";

    private final BasketService basketService;
    private final Scanner scanner;

    public ChangeQuantity(BasketService basketService, Scanner scanner) {
        this.basketService = basketService;
        this.scanner = scanner;
    }

    /**
     * Changes quantity of product in the basket by {@code id}
     */
    @Override
    public void execute() {
        boolean isChanged = false;
        System.out.println(CHOOSE_ID);

        do {
            try {
                long itemId = Long.parseLong(scanner.nextLine().trim());

                System.out.println(ENTER_QUANTITY);
                int quantity = Integer.parseInt(scanner.nextLine().trim());
                isChanged = basketService.updateAmount(itemId, quantity);
                System.out.println(SUCCESS_CHANGING);

                if (!isChanged) {
                    System.out.println(WRONG_ID);
                }
            } catch (NumberFormatException e) {
                System.out.println(NOT_NUMERICAL);
            }
        } while (!isChanged);
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }
}
