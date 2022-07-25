package com.epam.lab.shchehlov.task_07.shop.reflection.creator;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for storing names in container and choosing them when creating a new product.
 *
 * @author B.Shchehlov.
 */
public class ProductNameContainer {
    private final Map<Integer, String> container;

    public ProductNameContainer() {
        container = new HashMap<>();
        int key = 0;

        container.put(key++, PowerTool.class.getName());
        container.put(key++, CordedPowerTool.class.getName());
        container.put(key++, CordlessPowerTool.class.getName());
        container.put(key, Drill.class.getName());
    }

    /**
     * Returns the size of container.
     *
     * @return size of container.
     */
    public int getSize() {
        return container.size();
    }

    /**
     * Returns product name by product number
     *
     * @param productKey list product number
     * @return product name by product number
     */
    public String getProductName(int productKey) {
        if (!container.containsKey(productKey)) {
            throw new IllegalArgumentException();
        }
        return container.get(productKey);
    }

    /**
     * Returns all the values stored in the container.
     *
     * @return all the values stored in the container.
     */
    public Map<Integer, String> getAll() {
        return container;
    }
}
