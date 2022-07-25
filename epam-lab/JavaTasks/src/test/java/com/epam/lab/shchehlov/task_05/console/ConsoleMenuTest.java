package com.epam.lab.shchehlov.task_05.console;

import com.epam.lab.shchehlov.task_05.file.search.console.ConsoleMenu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ConsoleMenuTest {
    private static ByteArrayOutputStream baos;
    private ConsoleMenu consoleMenu;

    @BeforeClass
    public static void beforeClass() {
        baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    }

    @Before
    public void before() {
        consoleMenu = new ConsoleMenu();
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
    public void should() {
        String input = "src\\test\\resources\\task_05" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "wrong_name" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        String expectedOutput = "<<FILE SEARCHER>>" + System.lineSeparator() +
                "Enter the path to the folder:" + System.lineSeparator() +
                "Search by file name (N/Y): 0/1" + System.lineSeparator() +
                "Enter file name:" + System.lineSeparator() +
                "Search by file extension (N/Y): 0/1" + System.lineSeparator() +
                "Search by file size (N/Y): 0/1" + System.lineSeparator() +
                "Search by file by last modification date (N/Y): 0/1" + System.lineSeparator() +
                "No results were found according to these criteria" + System.lineSeparator() +
                "Search results:" + System.lineSeparator() +
                "Total: 0 files were found " + System.lineSeparator() +
                "Continue searching? (N/Y): 0/1" + System.lineSeparator() +
                "Bye!" + System.lineSeparator();

        consoleMenu.run();

        assertEquals(expectedOutput, baos.toString());
    }
}
