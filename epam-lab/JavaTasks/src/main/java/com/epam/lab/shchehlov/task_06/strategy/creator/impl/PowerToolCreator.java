package com.epam.lab.shchehlov.task_06.strategy.creator.impl;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_06.strategy.CreatorStrategy;
import com.epam.lab.shchehlov.task_06.strategy.creator.Creator;

import java.util.Scanner;

/**
 * Implementation of interface Creator for creating instance of PowerTool.
 *
 * @author B.Shchehlov
 */
public class PowerToolCreator implements Creator {
    private static final String DESCRIPTION = "Create Power Tool";

    private final CreatorStrategy strategy;
    private final Scanner scanner;

    public PowerToolCreator(CreatorStrategy strategy, Scanner scanner) {
        this.strategy = strategy;
        this.scanner = scanner;
    }

    @Override
    public PowerTool create() {
        return strategy.createPowerTool(scanner);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
