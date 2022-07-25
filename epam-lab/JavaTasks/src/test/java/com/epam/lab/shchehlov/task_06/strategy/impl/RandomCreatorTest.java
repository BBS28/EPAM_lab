package com.epam.lab.shchehlov.task_06.strategy.impl;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class RandomCreatorTest {
    private RandomCreator randomCreator;

    @Before
    public void before() {
        randomCreator = new RandomCreator();
    }

    @Test
    public void shouldCreateNewPowerToolWithGivenRangeParametersTenTimesInRow() {
        String expectedNameRegex = "\\b(Model)\\s([0-9]{6})\\b";
        int expectedMinPrice = 1000;
        int expectedMaxPrice = 10000;

        for (int i = 0; i < 10; i++) {
            PowerTool powerTool = randomCreator.createPowerTool(new Scanner(System.in));

            assertTrue(powerTool.getName().matches(expectedNameRegex));
            assertTrue(powerTool.getPrice() >= expectedMinPrice
                    && powerTool.getPrice() < expectedMaxPrice);
        }
    }

    @Test
    public void shouldCreateNewCordedPowerToolWithGivenRangeParametersTenTimesInRow() {
        int expectedMinPower = 500;
        int expectedMaxPower = 3500;

        for (int i = 0; i < 10; i++) {
            CordedPowerTool tool = randomCreator.createCordedPowerTool(new Scanner(System.in));

            assertTrue(tool.getPower() >= expectedMinPower
                    && tool.getPower() < expectedMaxPower);
        }
    }

    @Test
    public void shouldCreateNewCordlessPowerToolWithGivenRangeParametersTenTimesInRow() {
        int expectedMinVoltage = 12;
        int expectedMaxVoltage = 24;
        int expectedMinCapacity = 1500;
        int expectedMaxCapacity = 9000;

        for (int i = 0; i < 10; i++) {
            CordlessPowerTool tool = randomCreator.createCordlessPowerTool(new Scanner(System.in));

            assertTrue(tool.getVoltage() >= expectedMinVoltage
                    && tool.getVoltage() <= expectedMaxVoltage);
            assertTrue(tool.getBatteryCapacity() >= expectedMinCapacity
                    && tool.getBatteryCapacity() < expectedMaxCapacity);
        }
    }

    @Test
    public void shouldCreateNewDrillWithGivenRangeParametersTenTimesInRow() {
        int expectedMinMaxRPM = 1500;
        int expectedMaxMaxRPM = 3500;

        for (int i = 0; i < 10; i++) {
            Drill tool = randomCreator.createDrill(new Scanner(System.in));

            assertTrue(tool.getMaxRPM() >= expectedMinMaxRPM
                    && tool.getMaxRPM() <= expectedMaxMaxRPM);
        }
    }
}
