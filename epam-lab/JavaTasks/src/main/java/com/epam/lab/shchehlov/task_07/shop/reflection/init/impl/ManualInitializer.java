package com.epam.lab.shchehlov.task_07.shop.reflection.init.impl;

import com.epam.lab.shchehlov.task_07.shop.reflection.init.Initializer;

import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Class for initializing the corresponding product fields with values entered by the user.
 *
 * @author B.Shchehlov
 */
public class ManualInitializer implements Initializer {
    private final Scanner scanner;
    private final ResourceBundle resourceBundle;

    public ManualInitializer(Scanner scanner, ResourceBundle resourceBundle) {
        this.scanner = scanner;
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String initName() {
        do {
            String name = scanner.nextLine().trim();
            if (!name.equals("")) {
                return name;
            }
            System.out.println(resourceBundle.getString("NULL_VALUE"));
            System.out.println(resourceBundle.getString("REPEAT_ENTER"));
        } while (true);
    }

    @Override
    public int initPrice() {
        return initInteger();
    }

    @Override
    public int initPower() {
        return initInteger();
    }

    @Override
    public int initVoltage() {
        return initInteger();
    }

    @Override
    public int initBatteryCapacity() {
        return initInteger();
    }

    @Override
    public int initMaxRpm() {
        return initInteger();
    }

    /**
     * Returns the entered int value and validates invalid input.
     *
     * @return entered int value.
     */
    private int initInteger() {
        int value;
        do {
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= 0) {
                    return value;
                }
                System.out.println(resourceBundle.getString("NEGATIVE_NUMBER"));
            } catch (NumberFormatException e) {
                System.out.println(resourceBundle.getString("NOT_NUMERICAL"));
            }
            System.out.println(resourceBundle.getString("REPEAT_ENTER"));
        } while (true);

    }
}
