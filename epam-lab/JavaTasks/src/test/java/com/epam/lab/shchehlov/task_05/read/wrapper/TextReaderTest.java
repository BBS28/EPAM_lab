package com.epam.lab.shchehlov.task_05.read.wrapper;

import com.epam.lab.shchehlov.task_05.file.read.wrapper.TextReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class TextReaderTest {
    private static final String FILE_NAME = "test_text_file.txt";

    private static ByteArrayOutputStream baos;
    private TextReader textReader;

    @BeforeClass
    public static void beforeClass() {
        baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    }

    @Before
    public void before() {
        textReader = new TextReader(FILE_NAME);
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
    public void shouldReturnFirstLineFromFile() {
        String expectedLine = "first line";
        Iterator<String> iterator = textReader.iterator();

        String actualLine = iterator.next();

        assertEquals(expectedLine, actualLine);
    }

    @Test
    public void shouldReturnAllTextFromFile() {
        String expected = "first line\n" +
                "this is second\n" +
                "    The third;\n" +
                "fourth\n" +
                "THE LAST ONE!\n" +
                "и еще одна строка на русском\n";

        String actual = textReader.readAllFile();

        assertEquals(expected, actual);
    }
}
