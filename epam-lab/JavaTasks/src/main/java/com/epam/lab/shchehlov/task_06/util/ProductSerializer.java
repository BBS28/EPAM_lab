package com.epam.lab.shchehlov.task_06.util;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_06.exception.DeserializationException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

/**
 * Class for serializing products available in the console store.
 *
 * @author B.Shchehlov
 */
public class ProductSerializer {
    private static final String WARNING_SAVE = "Can not save the objects";
    private static final String WARNING_LOAD = "Can not load the objects";

    /**
     * Saves product information to a file at the specified path {@param filePath}.
     */
    public static void saveProducts(ProductService productService, String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            Hashtable<Long, PowerTool> productList = productService.getAll();
            PowerTool[] tools = productList.values().toArray(new PowerTool[0]);
            outputStream.writeObject(tools);
        } catch (IOException e) {
            System.out.println(WARNING_SAVE);
        }
    }

    /**
     * Returns previously saved product objects as an array from the specified path {@param filePath}.
     *
     * @throws DeserializationException if the file cannot be read.
     */
    public static PowerTool[] loadProducts(String filePath) {
        PowerTool[] products;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            products = (PowerTool[]) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new DeserializationException(WARNING_LOAD);
        }

        return products;
    }
}