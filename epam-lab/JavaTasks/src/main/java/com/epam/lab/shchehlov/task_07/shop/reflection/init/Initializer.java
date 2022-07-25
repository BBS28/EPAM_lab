package com.epam.lab.shchehlov.task_07.shop.reflection.init;

/**
 * Class for initializing the corresponding product fields.
 *
 * @author B.Shchehlov
 */
public interface Initializer {
    String initName();

    int initPrice();

    int initPower();

    int initVoltage();

    int initBatteryCapacity();

    int initMaxRpm();
}
