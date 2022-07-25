package com.epam.lab.shchehlov.task_06.strategy.impl;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_06.strategy.CreatorStrategy;
import com.epam.lab.shchehlov.task_06.util.AutoInitializer;

import java.util.Scanner;

/**
 * Specific strategy. Implements automatic input of product data by a random number generator.
 *
 * @author B.Shchehlov
 */
public class RandomCreator implements CreatorStrategy {
    private static final long DEFAULT_ID = 0L;

    @Override
    public PowerTool createPowerTool(Scanner scanner) {
        PowerTool powerTool = new PowerTool();

        powerTool.setName(AutoInitializer.initName());
        powerTool.setPrice(AutoInitializer.initPrice());

        return powerTool;
    }

    @Override
    public CordedPowerTool createCordedPowerTool(Scanner scanner) {
        PowerTool powerTool = createPowerTool(scanner);

        int power = AutoInitializer.initPower();

        return new CordedPowerTool(DEFAULT_ID, powerTool.getName(), powerTool.getPrice(), power);
    }

    @Override
    public CordlessPowerTool createCordlessPowerTool(Scanner scanner) {
        PowerTool powerTool = createPowerTool(scanner);

        int voltage = AutoInitializer.initVoltage();
        int capacity = AutoInitializer.initCapacity();

        return new CordlessPowerTool(DEFAULT_ID, powerTool.getName(), powerTool.getPrice(), voltage, capacity);
    }

    @Override
    public Drill createDrill(Scanner scanner) {
        CordedPowerTool cordedPowerTool = createCordedPowerTool(scanner);

        int maxRPM = AutoInitializer.initRPM();

        return new Drill(DEFAULT_ID, cordedPowerTool.getName(), cordedPowerTool.getPrice(), cordedPowerTool.getPower(), maxRPM);
    }
}
