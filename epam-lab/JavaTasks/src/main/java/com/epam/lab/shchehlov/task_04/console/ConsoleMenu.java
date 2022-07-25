package com.epam.lab.shchehlov.task_04.console;

import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.command.CommandContainer;
import com.epam.lab.shchehlov.task_06.command.CreateNewProductCommand;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_06.strategy.CreatorStrategy;
import com.epam.lab.shchehlov.task_06.strategy.creator.CreatorContainer;
import com.epam.lab.shchehlov.task_06.strategy.impl.ManualCreator;
import com.epam.lab.shchehlov.task_06.strategy.impl.RandomCreator;
import com.epam.lab.shchehlov.task_07.shop.reflection.command.CreateProductReflectionCommand;
import com.epam.lab.shchehlov.task_07.shop.reflection.creator.ProductNameContainer;
import com.epam.lab.shchehlov.task_07.shop.reflection.init.Initializer;
import com.epam.lab.shchehlov.task_07.shop.reflection.init.impl.AutoInitializer;
import com.epam.lab.shchehlov.task_07.shop.reflection.init.impl.ManualInitializer;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Class that implements user interface that processes incoming commands and lists the available commands
 *
 * @author B.Shchehlov
 */
public class ConsoleMenu {
    private static final int DEFAULT_VALUE = -1;
    private static final String WARNING_ENTER_NUMERICAL = "Please enter a numerical value!";
    private static final String MENU = "Menu:";
    private static final String POINTER = " -> ";
    private static final String CHOOSE_INSERT = "Choose a method for initializing new products (m - manual / a - automatic)";
    private static final String CHOOSE_BUNDLE = "Choose a localization (en - English / ru - Russian)";
    private static final String WARNING_INVALID_INSERT = "Choose only \"m\" for manual or \"a\" for automatic initialization";
    private static final String WARNING_INVALID_BUNDLE = "Choose only \"en\" for English or \"ru\" for Russian localization";
    private static final String MANUAL = "m";
    private static final String AUTOMATIC = "a";
    private static final String ENGLISH = "en";
    private static final String RUSSIAN = "ru";
    private static final String LOCALE = "locale";

    private final CommandContainer commandContainer;
    private final ProductService productService;
    private final Scanner scanner;
    private Initializer initializer;

    public ConsoleMenu(CommandContainer commandContainer, ProductService productService, Scanner scanner) {
        this.commandContainer = commandContainer;
        this.productService = productService;
        this.scanner = scanner;
    }

    /**
     * Prints all lists the available commands and processes the input of the selected command
     */
    public void processRun() {
        final ResourceBundle resourceBundle = chooseResourceBundle(scanner);
        final CreatorContainer creatorContainer = chooseCreateMethod(scanner, resourceBundle);
        final ProductNameContainer productNameContainer = new ProductNameContainer();
        commandContainer.addCommand(new CreateNewProductCommand(productService, creatorContainer, scanner));
        commandContainer.addCommand(new CreateProductReflectionCommand(productService, initializer, productNameContainer, resourceBundle, scanner));

        while (true) {
            printMenu();
            int commandId = DEFAULT_VALUE;

            try {
                commandId = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(WARNING_ENTER_NUMERICAL);
            }

            Command command = commandContainer.getCommand(commandId);
            if (commandId == 0) {
                command.execute();
                break;
            }
            command.execute();
        }
    }

    /**
     * Prints all lists the available commands
     */
    private void printMenu() {
        StringBuilder textMenu = new StringBuilder(MENU + System.lineSeparator());
        for (Map.Entry<Integer, Command> entry : commandContainer.getCommands().entrySet()) {
            if (entry.getKey() > DEFAULT_VALUE) {
                textMenu.append(entry.getKey()).append(POINTER);
                Command command = entry.getValue();
                textMenu.append(command.getDescription()).append(System.lineSeparator());
            }
        }
        System.out.println(textMenu);
    }

    /**
     * Creates CreatorContainer according to the chosen strategy.
     *
     * @return CreatorContainer with commands for creating products.
     */
    private CreatorContainer chooseCreateMethod(Scanner scanner, ResourceBundle resourceBundle) {
        CreatorContainer creatorContainer;
        CreatorStrategy strategy;
        System.out.println(CHOOSE_INSERT);

        do {
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case MANUAL:
                    strategy = new ManualCreator();
                    creatorContainer = new CreatorContainer(strategy, scanner);
                    initializer = new ManualInitializer(scanner, resourceBundle);
                    return creatorContainer;
                case AUTOMATIC:
                    strategy = new RandomCreator();
                    creatorContainer = new CreatorContainer(strategy, scanner);
                    initializer = new AutoInitializer();
                    return creatorContainer;
                default:
                    System.out.println(WARNING_INVALID_INSERT);
                    break;
            }
        } while (true);
    }

    /**
     * Creates ResourceBundle according to the chosen option.
     *
     * @param scanner for input values from console
     * @return resourceBundle with values according to the chosen language.
     */
    private ResourceBundle chooseResourceBundle(Scanner scanner) {
        ResourceBundle resourceBundle;
        System.out.println(CHOOSE_BUNDLE);

        do {
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case ENGLISH:
                    resourceBundle = ResourceBundle.getBundle(LOCALE, new Locale(ENGLISH));
                    return resourceBundle;
                case RUSSIAN:
                    resourceBundle = ResourceBundle.getBundle(LOCALE, new Locale(RUSSIAN));
                    return resourceBundle;
                default:
                    System.out.println(WARNING_INVALID_BUNDLE);
                    break;
            }
        }while (true);
    }
}
