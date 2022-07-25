package com.epam.lab.shchehlov.task_04.command;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CommandContainerTest {
    private static final int COMMAND_NUMBER = 0;

    private CommandContainer commandContainer;
    private static ByteArrayOutputStream baos;

    @BeforeClass
    public static void beforeClass() {
        baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    }

    @Before
    public void before() {
        commandContainer = new CommandContainer();
        Command command = new Command() {
            @Override
            public void execute() {
                System.out.print("Hello");
            }

            @Override
            public String getDescription() {
                return "Say Hello";
            }
        };
        commandContainer.addCommand(command);
    }

    @After
    public void after() {
        baos.reset();
    }

    @AfterClass
    public static void afterClass() {
        baos.reset();
        System.setOut(System.out);
    }

    @Test
    public void shouldPrintSayHelloCommand() {
        String expected = "Hello";

        commandContainer.getCommand(COMMAND_NUMBER).execute();

        assertEquals(expected, baos.toString());
    }
}
