package com.epam.lab.shchehlov.task_04.command.impl;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.service.AddedProductsService;
import com.epam.lab.shchehlov.task_04.service.BasketService;
import com.epam.lab.shchehlov.task_04.service.ProductService;

import java.util.Scanner;

/**
 * Implementation of interface Command for adding new product to the basket
 *
 * @author B.Shchehlov
 */
public class AddToBasketCommand implements Command {
    private static final String COMMAND_NAME = "Add product to basket";
    private static final String ENTER_ID = "Enter item ID:";
    private static final String SUCCESS_ADDED = "Product added to basket!";
    private static final String NOT_EXIST = "Product with this ID does not exist! Repeat enter please";
    private static final String NOT_NUMERICAL = "Please enter a numerical value!";

    private final ProductService productService;
    private final BasketService basketService;
    private final AddedProductsService addedProductService;
    private final Scanner scanner;

    public AddToBasketCommand(ProductService productService, BasketService basketService, AddedProductsService addedProductService, Scanner scanner) {
        this.productService = productService;
        this.basketService = basketService;
        this.addedProductService = addedProductService;
        this.scanner = scanner;
    }

    /**
     * Adds new product to the basket
     */
    @Override
    public void execute() {
        System.out.println(ENTER_ID);

        do {
            try {
                long itemId = Long.parseLong(scanner.nextLine().trim());
                if (productService.getOne(itemId) != null) {
                    basketService.add(itemId);
                    PowerTool powerTool = productService.getOne(itemId);
                    addedProductService.insert(itemId, powerTool);
                    System.out.println(SUCCESS_ADDED);
                    break;
                } else {
                    System.out.println(NOT_EXIST);
                }
            } catch (NumberFormatException ex) {
                System.out.println(NOT_NUMERICAL);
            }
        } while (true);
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }
}
