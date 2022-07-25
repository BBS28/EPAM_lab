package com.epam.lab.shchehlov.task_06.strategy;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;

import java.util.Scanner;

/**
 * Common interface for all strategies for entering new product data.
 *
 * @author B.Shchehlov
 */
public interface CreatorStrategy {

    /**
     * Creates new PowerTool and initializes it.
     *
     * @return PowerTool object
     */
    PowerTool createPowerTool(Scanner scanner);

    /**
     * Creates new CordedPowerTool and initializes it.
     *
     * @return CordedPowerTool object
     */
    CordedPowerTool createCordedPowerTool(Scanner scanner);

    /**
     * Creates new CordlessPowerTool and initializes it.
     *
     * @return CordlessPowerTool object
     */
    CordlessPowerTool createCordlessPowerTool(Scanner scanner);

    /**
     * Creates new Drill and initializes it.
     *
     * @return Drill object
     */
    Drill createDrill(Scanner scanner);
}
