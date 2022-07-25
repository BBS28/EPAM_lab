package com.epam.lab.shchehlov.task_04.command.Impl;

import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.command.impl.NoCommand;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class NoCommandTest {
    private Command command;
    private static ByteArrayOutputStream baos;

    @BeforeClass
    public static void beforeClass() {
        baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    }

    @Before
    public void before() {
        command = new NoCommand();
    }

    @After
    public void after() {
        baos.reset();
    }

    @AfterClass
    public static void afterClass() {
        System.setOut(System.out);
    }

    @Test
    public void shouldPrintNoCommand() {
        String expected = "No command number was entered. Enter correct number!" + System.lineSeparator();

        command.execute();

        assertEquals(expected, baos.toString());
    }
}
