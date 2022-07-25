package com.epam.lab.shchehlov.task_06.strategy.impl;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_06.strategy.CreatorStrategy;

import java.util.Scanner;

/**
 * Specific strategy. Implements manual entry of product data.
 *
 * @author B.Shchehlov
 */
public class ManualCreator implements CreatorStrategy {
    private static final long DEFAULT_ID = 0L;
    private static final String ERROR_ENTER_INTEGER = "Please enter a numerical value!";
    private static final String ERROR_ENTER_POSITIVE = "Please enter positive value!";
    private static final String ERROR_ENTER_NOT_EMPTY = "Please enter not empty value!";
    private static final String ENTER_NAME = "Enter product's name:";
    private static final String ENTER_PRICE = "Enter product's price:";
    private static final String ENTER_POWER = "Enter product's power:";
    private static final String ENTER_VOLTAGE = "Enter product's battery voltage:";
    private static final String ENTER_CAPACITY = "Enter product's battery capacity:";
    private static final String ENTER_RPM = "Enter product's max RPM:";
    private static final String EMPTY_STRING = "";

    @Override
    public PowerTool createPowerTool(Scanner scanner) {
        PowerTool powerTool = new PowerTool();

        System.out.println(ENTER_NAME);
        powerTool.setName(initString(scanner));

        System.out.println(ENTER_PRICE);
        powerTool.setPrice(initInteger(scanner));

        return powerTool;
    }

    @Override
    public CordedPowerTool createCordedPowerTool(Scanner scanner) {
        PowerTool powerTool = createPowerTool(scanner);

        System.out.println(ENTER_POWER);
        int power = initInteger(scanner);

        return new CordedPowerTool(DEFAULT_ID, powerTool.getName(), powerTool.getPrice(), power);
    }

    @Override
    public CordlessPowerTool createCordlessPowerTool(Scanner scanner) {
        PowerTool powerTool = createPowerTool(scanner);

        System.out.println(ENTER_VOLTAGE);
        int voltage = initInteger(scanner);

        System.out.println(ENTER_CAPACITY);
        int capacity = initInteger(scanner);

        return new CordlessPowerTool(DEFAULT_ID, powerTool.getName(), powerTool.getPrice(), voltage, capacity);
    }

    @Override
    public Drill createDrill(Scanner scanner) {
        CordedPowerTool cordedPowerTool = createCordedPowerTool(scanner);

        System.out.println(ENTER_RPM);
        int maxRPM = initInteger(scanner);

        return new Drill(DEFAULT_ID, cordedPowerTool.getName(), cordedPowerTool.getPrice(), cordedPowerTool.getPower(), maxRPM);
    }

    /**
     * Validates input int value for initialize parameters of product
     *
     * @return int value
     */
    private static int initInteger(Scanner scanner) {
        int value;

        do {
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= 0) {
                    return value;
                }
                System.out.println(ERROR_ENTER_POSITIVE);
            } catch (NumberFormatException e) {
                System.out.println(ERROR_ENTER_INTEGER);
            }
        } while (true);
    }

    /**
     * Validates input String value for initialize parameters of product.
     *
     * @return String value
     */
    private static String initString(Scanner scanner) {
        do {
            String name = scanner.nextLine().trim();
            if (!name.equals(EMPTY_STRING)) {
                return name;
            }
            System.out.println(ERROR_ENTER_NOT_EMPTY);
        } while (true);
    }
}
