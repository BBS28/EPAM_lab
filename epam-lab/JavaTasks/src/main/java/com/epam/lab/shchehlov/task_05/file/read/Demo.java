package com.epam.lab.shchehlov.task_05.file.read;

import com.epam.lab.shchehlov.task_05.file.read.wrapper.TextReader;

/**
 * A class that demonstrates how the text file wrapper works.
 *
 * @author B.Shchehlov
 */
public class Demo {
    private static final String FILE_NAME = "test_text_file.txt";
    private static final String DELIMITER = "----------------------------------------";

    public static void main(String[] args) {
        TextReader textReader = new TextReader(FILE_NAME);
        System.out.print(textReader.readAllFile());
        System.out.println(DELIMITER);
        for (String line : textReader) {
            System.out.println(line);
        }
    }
}