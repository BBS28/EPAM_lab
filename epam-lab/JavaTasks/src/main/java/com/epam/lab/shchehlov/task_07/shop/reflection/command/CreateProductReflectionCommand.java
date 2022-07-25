package com.epam.lab.shchehlov.task_07.shop.reflection.command;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_07.shop.reflection.constant.Constant;
import com.epam.lab.shchehlov.task_07.shop.reflection.creator.ProductNameContainer;
import com.epam.lab.shchehlov.task_07.shop.reflection.creator.ReflectionCreator;
import com.epam.lab.shchehlov.task_07.shop.reflection.init.Initializer;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Class that creates new product with the help of reflection and adds it to the console shop.
 *
 * @author B.Shchehlov
 */
public class CreateProductReflectionCommand implements Command {
    private static final String COMMAND_NAME = "Create new product with reflection";
    private static final String CREATING_PRODUCT_FORMAT = "%s %s:%n";

    private final ProductService productService;
    private final Initializer initializer;
    private final ProductNameContainer productNameContainer;
    private final ResourceBundle resourceBundle;
    private final Scanner scanner;

    public CreateProductReflectionCommand(ProductService productService,
                                          Initializer initializer,
                                          ProductNameContainer productNameContainer,
                                          ResourceBundle resourceBundle,
                                          Scanner scanner) {
        this.productService = productService;
        this.initializer = initializer;
        this.productNameContainer = productNameContainer;
        this.resourceBundle = resourceBundle;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println(resourceBundle.getString("SELECT_CREATOR").concat(Constant.COLON));
        printMenu(productNameContainer);

        do {
            try {
                int productNumber = Integer.parseInt(scanner.nextLine().trim());

                if (productNumber >= 0 && productNumber < productNameContainer.getSize()) {
                    String productClazzName = productNameContainer.getProductName(productNumber);
                    System.out.printf(CREATING_PRODUCT_FORMAT, resourceBundle.getString("CREATING"), resourceBundle.getString(productClazzName));
                    ReflectionCreator reflectionCreator = new ReflectionCreator(productClazzName, initializer, resourceBundle);
                    PowerTool powerTool = reflectionCreator.create();
                    productService.insert(powerTool);

                    System.out.println(resourceBundle.getString("CREATED"));
                    System.out.println(powerTool);
                    break;
                } else {
                    System.out.println(resourceBundle.getString("NOT_EXIST"));
                }
            } catch (NumberFormatException ex) {
                System.out.println(resourceBundle.getString("NOT_NUMERICAL"));
            }
            System.out.println(resourceBundle.getString("REPEAT_ENTER"));
        } while (true);
    }

    @Override
    public String getDescription() {
        return COMMAND_NAME;
    }

    private void printMenu(ProductNameContainer productNameContainer) {
        StringBuilder textMenu = new StringBuilder();

        for (Map.Entry<Integer, String> entry : productNameContainer.getAll().entrySet()) {
            String productName = resourceBundle.getString(entry.getValue());
            textMenu.append(entry.getKey())
                    .append(Constant.POINTER)
                    .append(resourceBundle.getString("CREATE"))
                    .append(Constant.SPACE)
                    .append(productName)
                    .append(System.lineSeparator());
        }

        System.out.println(textMenu);
    }
}
