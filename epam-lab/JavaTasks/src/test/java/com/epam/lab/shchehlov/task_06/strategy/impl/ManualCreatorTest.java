package com.epam.lab.shchehlov.task_06.strategy.impl;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ManualCreatorTest {
    private ManualCreator creator;

    @Before
    public void before() {
        creator = new ManualCreator();
    }

    @Test
    public void shouldCreatePowerToolWithCertainParameters() {
        String input = "tool 123456" + System.lineSeparator() +
                "1000" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        PowerTool expectedTool = new PowerTool(0L, "tool 123456", 1000);

        PowerTool tool = creator.createPowerTool(new Scanner(System.in));

        assertEquals(expectedTool, tool);
    }

    @Test
    public void shouldCreateCordedPowerToolWithCertainParameters() {
        String input = "tool 123456" + System.lineSeparator() +
                "1000" + System.lineSeparator() +
                "1100" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        CordedPowerTool expectedTool = new CordedPowerTool(0L, "tool 123456", 1000, 1100);

        CordedPowerTool tool = creator.createCordedPowerTool(new Scanner(System.in));

        assertEquals(expectedTool, tool);
    }

    @Test
    public void shouldCreateCordlessPowerToolWithCertainParameters() {
        String input = "tool 123456" + System.lineSeparator() +
                "1000" + System.lineSeparator() +
                "12" + System.lineSeparator() +
                "1500" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        CordlessPowerTool expectedTool = new CordlessPowerTool(0L, "tool 123456", 1000, 12, 1500);

        CordlessPowerTool tool = creator.createCordlessPowerTool(new Scanner(System.in));

        assertEquals(expectedTool, tool);
    }

    @Test
    public void shouldCreateDrillWithCertainParameters() {
        String input = "tool 123456" + System.lineSeparator() +
                "1000" + System.lineSeparator() +
                "1100" + System.lineSeparator() +
                "2000" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        Drill expectedTool = new Drill(0L, "tool 123456", 1000, 1100, 2000);

        Drill tool = creator.createDrill(new Scanner(System.in));

        assertEquals(expectedTool, tool);
    }
}
