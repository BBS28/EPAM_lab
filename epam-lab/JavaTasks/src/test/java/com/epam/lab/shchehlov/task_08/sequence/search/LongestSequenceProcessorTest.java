package com.epam.lab.shchehlov.task_08.sequence.search;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class LongestSequenceProcessorTest {
    private static ByteArrayOutputStream byteArrayOutputStream;

    @BeforeClass
    public static void beforeClass() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @After
    public void after() {
        byteArrayOutputStream.reset();
    }

    @AfterClass
    public static void afterClass() {
        System.setOut(System.out);
    }

    @Test
    public void shouldPrintResultsOfSearchingWhenProcess() {
        String input = "src\\test\\resources\\task_08\\byte_sequence_01.txt" + System.lineSeparator() + "0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String expectedOutput = "Please enter path to file, or enter 0 to exit:" + System.lineSeparator() +
                "Current value of the sequence length - 2" + System.lineSeparator() +
                "Current value of the sequence length - 3" + System.lineSeparator() +
                "Current value of the sequence length - 4" + System.lineSeparator() +
                "Current value of the sequence length - 5" + System.lineSeparator() +
                "Current value of the sequence length - 6" + System.lineSeparator() +
                "Repeating sequence size - 6, first entry index - 4, second entry index - 84" + System.lineSeparator() +
                "Please enter path to file, or enter 0 to exit:" + System.lineSeparator();

        LongestSequenceProcessor processor = new LongestSequenceProcessor(new Scanner(System.in));
        processor.process();

        assertEquals(expectedOutput, byteArrayOutputStream.toString());
    }
}
