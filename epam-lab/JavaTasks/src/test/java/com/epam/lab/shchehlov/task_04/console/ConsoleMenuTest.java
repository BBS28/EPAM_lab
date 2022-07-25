package com.epam.lab.shchehlov.task_04.console;

import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.command.CommandContainer;
import com.epam.lab.shchehlov.task_04.command.impl.NoCommand;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.impl.ProductServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ConsoleMenuTest {
    private static ByteArrayOutputStream baos;
    private CommandContainer commandContainer;

    @BeforeClass
    public static void beforeClass() {
        baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    }

    @Before
    public void before() {
        commandContainer = new CommandContainer();
        commandContainer.addCommand(new NoCommand());
        Command command1 = new Command() {
            @Override
            public void execute() {
                System.out.println("Hello");
            }

            @Override
            public String getDescription() {
                return "Say Hello";
            }
        };
        commandContainer.addCommand(command1);
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
    public void shouldPrintHelloAndThenExitWhenProcessRun() {
        String input = "en" + System.lineSeparator() +
                "a" + System.lineSeparator() +
                "1" + System.lineSeparator() + "0";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        String expected = "Choose a localization (en - English / ru - Russian)" + System.lineSeparator() +
                "Choose a method for initializing new products (m - manual / a - automatic)" + System.lineSeparator() +
                "Menu:" + System.lineSeparator() +
                "0 -> No command entered" + System.lineSeparator() +
                "1 -> Say Hello" + System.lineSeparator() +
                "2 -> Create new product" + System.lineSeparator() +
                "3 -> Create new product with reflection" + System.lineSeparator() +
                System.lineSeparator() +
                "Hello" + System.lineSeparator() +
                "Menu:" + System.lineSeparator() +
                "0 -> No command entered" + System.lineSeparator() +
                "1 -> Say Hello" + System.lineSeparator() +
                "2 -> Create new product" + System.lineSeparator() +
                "3 -> Create new product with reflection" + System.lineSeparator() +
                System.lineSeparator() +
                "No command number was entered. Enter correct number!" + System.lineSeparator();
        ConsoleMenu consoleMenu = new ConsoleMenu(commandContainer, new ProductServiceImpl(new ProductRepositoryImpl()), new Scanner(System.in));

        consoleMenu.processRun();

        assertEquals(expected, baos.toString());
    }
}
