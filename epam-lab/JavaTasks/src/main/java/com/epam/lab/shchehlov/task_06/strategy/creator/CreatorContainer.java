package com.epam.lab.shchehlov.task_06.strategy.creator;

import com.epam.lab.shchehlov.task_06.strategy.CreatorStrategy;
import com.epam.lab.shchehlov.task_06.strategy.creator.impl.CordedPowerToolCreator;
import com.epam.lab.shchehlov.task_06.strategy.creator.impl.CordlessPowerToolCreator;
import com.epam.lab.shchehlov.task_06.strategy.creator.impl.DrillCreator;
import com.epam.lab.shchehlov.task_06.strategy.creator.impl.PowerToolCreator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Container for storing commands for Creator with assigned access keys.
 *
 * @author B.Shchehlov
 */
public class CreatorContainer {
    private final Scanner scanner;
    private final Map<Integer, Creator> container;

    public CreatorContainer(CreatorStrategy strategy, Scanner scanner) {
        this.scanner = scanner;
        this.container = new HashMap<>();
        int key = 0;

        container.put(key++, new PowerToolCreator(strategy, scanner));
        container.put(key++, new CordedPowerToolCreator(strategy, scanner));
        container.put(key++, new CordlessPowerToolCreator(strategy, scanner));
        container.put(key, new DrillCreator(strategy, scanner));
    }

    /**
     * Returns int size of container.
     *
     * @return int size of container.
     */
    public int getSize() {
        return container.size();
    }

    /**
     * Returns Creator by the key.
     *
     * @return Creator by the key.
     */
    public Creator getCreator(int creatorKey) {
        if (!container.containsKey(creatorKey)) {
            throw new IllegalArgumentException();
        }
        return container.get(creatorKey);
    }

    /**
     * Returns Map of creators in the container.
     *
     * @return Map of creators in the container.
     */
    public Map<Integer, Creator> getAll() {
        return container;
    }
}
