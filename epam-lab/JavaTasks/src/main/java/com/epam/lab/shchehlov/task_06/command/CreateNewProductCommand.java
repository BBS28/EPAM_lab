package com.epam.lab.shchehlov.task_06.command;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_06.strategy.creator.CreatorContainer;
import com.epam.lab.shchehlov.task_06.strategy.creator.Creator;

import java.util.Map;
import java.util.Scanner;

public class CreateNewProductCommand implements Command {
    private static final String COMMAND_NAME = "Create new product";
    private static final String CREATED = "Product %s successfully created!%n";
    private static final String POINTER = " -> ";
    private static final String SELECT_CREATOR = "Select the tool category you want to add:";
    private static final String NOT_EXIST = "Please choose correct number!";
    private static final String NOT_NUMERICAL = "Please enter a numerical value!";

    private final ProductService productService;
    private final CreatorContainer creatorContainer;
    private final Scanner scanner;

    /**
     * Creates new product and adds it to ProductRepository
     */
    public CreateNewProductCommand(ProductService productService, CreatorContainer creatorContainer, Scanner scanner) {
        this.productService = productService;
        this.creatorContainer = creatorContainer;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println(SELECT_CREATOR);
        printMenu(creatorContainer);
        do {
            try {
                int creatorNumber = Integer.parseInt(scanner.nextLine().trim());
                if (creatorNumber >= 0 && creatorNumber < creatorContainer.getSize()) {
                    Creator creator = creatorContainer.getCreator(creatorNumber);
                    PowerTool powerTool = creator.create();
                    productService.insert(powerTool);
                    System.out.printf(CREATED, powerTool);
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

    private void printMenu(CreatorContainer creatorContainer) {
        StringBuilder textMenu = new StringBuilder();
        for (Map.Entry<Integer, Creator> entry : creatorContainer.getAll().entrySet()) {
                textMenu.append(entry.getKey()).append(POINTER);
                Creator creator = entry.getValue();
                textMenu.append(creator.getDescription()).append(System.lineSeparator());
        }
        System.out.println(textMenu);
    }
}
