package com.epam.lab.shchehlov.task_06;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.app.ConsoleShop;
import com.epam.lab.shchehlov.task_06.constant.Constant;
import com.epam.lab.shchehlov.task_06.exception.DeserializationException;
import com.epam.lab.shchehlov.task_06.util.ProductSerializer;

public class Main {
    private static final String FILE_NOT_FOUND = " Data file not found or damaged!";

    public static void main(String[] args) {
        try {
            PowerTool[] powerTools = ProductSerializer.loadProducts(Constant.PRODUCT_DATA_FILE);
            ConsoleShop consoleShop = new ConsoleShop
                    .ConsoleShopBuilder()
                    .setProductService(powerTools)
                    .build();

            consoleShop.getConsoleMenu().processRun();
        } catch (DeserializationException e) {
            System.out.println(e + FILE_NOT_FOUND);
        }
    }
}
