package com.epam.lab.shchehlov.task_05.search.app;

import com.epam.lab.shchehlov.task_05.file.search.app.Searcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class SearcherTest {
    private static ByteArrayOutputStream baos;
    private Searcher searcher;


    @BeforeClass
    public static void beforeClass() {
        baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    }

    @Before
    public void before() {
        searcher = new Searcher();
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
    public void shouldFindAndOutputFileSearchedByName() {
        String input = "src\\test\\resources\\task_05" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "file_2" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);
        String expectedOutput = "file_2.txt" + System.lineSeparator();

        List<File> fileList = searcher.process(scanner);

        StringBuilder outputBuilder = new StringBuilder();
        for (File file : fileList) {
            outputBuilder.append(file.getName()).append(System.lineSeparator());
        }
        String actualOutput = outputBuilder.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldFindAndOutputFilesSearchedByExtension() {
        String input = "src\\test\\resources\\task_05" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "txt" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);
        String expectedOutput = "file_1.txt" + System.lineSeparator() +
                "file_2.txt" + System.lineSeparator();

        List<File> fileList = searcher.process(scanner);

        StringBuilder outputBuilder = new StringBuilder();
        for (File file : fileList) {
            outputBuilder.append(file.getName()).append(System.lineSeparator());
        }
        String actualOutput = outputBuilder.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldFindAndOutputFilesSearchedBySize() {
        String input = "src\\test\\resources\\task_05" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "40" + System.lineSeparator() +
                "60" + System.lineSeparator() +
                "0" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);
        String expectedOutput = "file_1.txt" + System.lineSeparator();

        List<File> fileList = searcher.process(scanner);

        StringBuilder outputBuilder = new StringBuilder();
        for (File file : fileList) {
            outputBuilder.append(file.getName()).append(System.lineSeparator());
        }
        String actualOutput = outputBuilder.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldFindAndOutputFilesSearchedByLastModifyingDate() {
        String input = "src\\test\\resources\\task_05" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "2021" + System.lineSeparator() +
                "11" + System.lineSeparator() +
                "30" + System.lineSeparator() +
                "9" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "2025" + System.lineSeparator() +
                "11" + System.lineSeparator() +
                "30" + System.lineSeparator() +
                "18" + System.lineSeparator() +
                "0" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);
        String expectedOutput = "file_1.txt" + System.lineSeparator() +
                "file_2.txt" + System.lineSeparator() +
                "file_qwerty.uio" + System.lineSeparator() +
                "file_test.abc" + System.lineSeparator();
        List<File> fileList = searcher.process(scanner);

        StringBuilder outputBuilder = new StringBuilder();
        for (File file : fileList) {
            outputBuilder.append(file.getName()).append(System.lineSeparator());
        }
        String actualOutput = outputBuilder.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldFindAndOutputFileSearchedByAllParameters() {
        String input = "src\\test\\resources\\task_05" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "file" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "uio" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "100" + System.lineSeparator() +
                "200" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "2021" + System.lineSeparator() +
                "11" + System.lineSeparator() +
                "30" + System.lineSeparator() +
                "9" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "2025" + System.lineSeparator() +
                "11" + System.lineSeparator() +
                "30" + System.lineSeparator() +
                "18" + System.lineSeparator() +
                "0" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);
        String expectedOutput = "file_qwerty.uio" + System.lineSeparator();
        List<File> fileList = searcher.process(scanner);

        StringBuilder outputBuilder = new StringBuilder();
        for (File file : fileList) {
            outputBuilder.append(file.getName()).append(System.lineSeparator());
        }
        String actualOutput = outputBuilder.toString();
        assertEquals(expectedOutput, actualOutput);
    }
}
