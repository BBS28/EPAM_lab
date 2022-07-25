package com.epam.lab.shchehlov.task_06.strategy.creator.impl;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_06.strategy.CreatorStrategy;
import com.epam.lab.shchehlov.task_06.strategy.creator.Creator;

import java.util.Scanner;

/**
 * Implementation of interface Creator for creating instance of CordedPowerTool.
 *
 * @author B.Shchehlov
 */
public class CordedPowerToolCreator implements Creator {
    private static final String DESCRIPTION = "Create Corded Power Tool";

    private final CreatorStrategy strategy;
    private final Scanner scanner;

    public CordedPowerToolCreator(CreatorStrategy strategy, Scanner scanner) {
        this.strategy = strategy;
        this.scanner = scanner;
    }

    @Override
    public PowerTool create() {
        return strategy.createCordedPowerTool(scanner);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
