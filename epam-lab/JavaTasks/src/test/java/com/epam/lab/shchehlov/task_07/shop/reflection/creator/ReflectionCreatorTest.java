package com.epam.lab.shchehlov.task_07.shop.reflection.creator;

import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_07.shop.reflection.init.impl.AutoInitializer;
import com.epam.lab.shchehlov.task_07.shop.reflection.init.impl.ManualInitializer;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReflectionCreatorTest {
    private static final String ENGLISH = "en";
    private static final String LOCALE = "locale";

    @Test
    public void shouldCreateCordlessPowerToolWithCertainParameters () {
        String input = "24" + System.lineSeparator() +
                "2000" + System.lineSeparator() +
                "tool 1000" + System.lineSeparator() +
                "1500" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE, new Locale(ENGLISH));
        ReflectionCreator reflectionCreator = new ReflectionCreator(CordlessPowerTool.class.getName(),
                new ManualInitializer(new Scanner(System.in), resourceBundle),
                resourceBundle);

        PowerTool powerTool = reflectionCreator.create();

        assertEquals(new CordlessPowerTool(0L, "tool 1000", 1500, 24, 2000), powerTool);
    }

    @Test
    public void shouldCreateDrillWithCertainParameters () {
        String input = "3000" + System.lineSeparator() +
                "wrong" + System.lineSeparator() +
                "2000" + System.lineSeparator() +
                "tool 1000" + System.lineSeparator() +
                "1500" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE, new Locale(ENGLISH));
        ReflectionCreator reflectionCreator = new ReflectionCreator(Drill.class.getName(),
                new ManualInitializer(new Scanner(System.in), resourceBundle),
                resourceBundle);

        PowerTool powerTool = reflectionCreator.create();

        assertEquals(new Drill(0L, "tool 1000", 1500, 2000, 3000), powerTool);
    }

    @Test
    public void shouldCreateDrillWithRandomParameters () {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE, new Locale(ENGLISH));
        ReflectionCreator reflectionCreator = new ReflectionCreator(Drill.class.getName(),
                new AutoInitializer(),
                resourceBundle);

        assertNotNull(reflectionCreator.create());
    }

    @Test
    public void shouldCreateCordlessPowerToolWithRandomParameters () {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE, new Locale(ENGLISH));
        ReflectionCreator reflectionCreator = new ReflectionCreator(CordlessPowerTool.class.getName(),
                new AutoInitializer(),
                resourceBundle);

        assertNotNull(reflectionCreator.create());
    }
 }
