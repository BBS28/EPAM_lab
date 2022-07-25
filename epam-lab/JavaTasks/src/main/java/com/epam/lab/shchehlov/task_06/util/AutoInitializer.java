package com.epam.lab.shchehlov.task_06.util;

/**
 * Class for generating data for instances of PowerTool, and it's inheritors.
 *
 * @author B.Shchehlov
 */
public class AutoInitializer {
    private static final String DEFAULT_NAME = "Model ";
    private static final int MIN_MODEL_NUMBER = 100000;
    private static final int MODEL_NUMBER_STEP = 900000;
    private static final int MIN_PRICE = 1000;
    private static final int PRICE_RANGE = 9000;
    private static final int MIN_POWER = 500;
    private static final int POWER_STEP = 100;
    private static final int POWER_STEP_NUMBER = 30;
    private static final int MIN_VOLTAGE = 12;
    private static final int VOLTAGE_STEP = 6;
    private static final int VOLTAGE_STEP_NUMBER = 3;
    private static final int MIN_CAPACITY = 1500;
    private static final int CAPACITY_STEP = 500;
    private static final int CAPACITY_STEP_NUMBER = 15;
    private static final int MIN_RPM = 1500;
    private static final int RPM_STEP = 100;
    private static final int RPM_STEP_NUMBER = 20;

    /**
     * Returns generated Name of product.
     *
     * @return String name.
     */
    public static String initName() {
        int modelNumber = (int) (MIN_MODEL_NUMBER + (Math.random() * MODEL_NUMBER_STEP));
        return DEFAULT_NAME + modelNumber;
    }

    /**
     * Returns generated price.
     *
     * @return int price.
     */
    public static int initPrice() {
        return (int) (MIN_PRICE + Math.random() * PRICE_RANGE);
    }

    /**
     * Returns generated power.
     *
     * @return int power.
     */
    public static int initPower() {
        return MIN_POWER + (int) (Math.random() * POWER_STEP_NUMBER) * POWER_STEP;
    }

    /**
     * Returns generated voltage.
     *
     * @return int voltage.
     */
    public static int initVoltage() {
        return MIN_VOLTAGE + (int) (Math.random() * VOLTAGE_STEP_NUMBER) * VOLTAGE_STEP;
    }

    /**
     * Returns generated capacity.
     *
     * @return int capacity.
     */
    public static int initCapacity() {
        return MIN_CAPACITY + (int) (Math.random() * CAPACITY_STEP_NUMBER) * CAPACITY_STEP;
    }

    /**
     * Returns generated maxRPM.
     *
     * @return int maxRPM.
     */
    public static int initRPM() {
        return MIN_RPM + (int) (Math.random() * RPM_STEP_NUMBER) * RPM_STEP;
    }
}
