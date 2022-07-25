package com.epam.lab.shchehlov.task_09;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.app.ConsoleShop;
import com.epam.lab.shchehlov.task_06.exception.DeserializationException;
import com.epam.lab.shchehlov.task_06.util.ProductSerializer;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import static com.epam.lab.shchehlov.task_09.constant.Constant.FILE_NOT_FOUND;
import static com.epam.lab.shchehlov.task_09.constant.Constant.PRODUCT_DATA_FILE;

/**
 * Class that launches the execution of the application.
 *
 * @author B.Shchehlov
 */
public class ShopApp {
    private static final Logger log = Logger.getLogger(ShopApp.class.getName());

    public static void main(String[] args) {
        try {
            PowerTool[] powerTools = ProductSerializer.loadProducts(PRODUCT_DATA_FILE);
            ConsoleShop consoleShop = new ConsoleShop
                    .ConsoleShopBuilder()
                    .setProductService(powerTools)
                    .build();
            consoleShop.getConsoleMenu().processRun();
        } catch (DeserializationException e) {
            log.error(FILE_NOT_FOUND + ExceptionUtils.getStackTrace(e));
        }
    }
}
