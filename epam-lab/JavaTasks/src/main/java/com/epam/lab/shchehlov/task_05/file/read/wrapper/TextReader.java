package com.epam.lab.shchehlov.task_05.file.read.wrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Wrapper class for viewing a text file.
 *
 * @author B.Shchehlov
 */
public class TextReader implements Iterable<String> {
    private static final String FILE_NOT_FOUND = "Can not find file with name %s%n";

    private final String fileName;

    public TextReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads all file.
     *
     * @return the text content of the file.
     */
    public String readAllFile() {
        StringBuilder result = new StringBuilder();
        for (String line : this) {
            result.append(line).append('\n');
        }
        return result.toString();
    }

    @Override
    public Iterator<String> iterator() {
        return new LineIterator();
    }

    /**
     * Allows reading the file line by line
     */
    private class LineIterator implements Iterator<String> {

        private Scanner reader;

        public LineIterator() {
            try {
                reader = new Scanner(new File(fileName), StandardCharsets.UTF_8.toString());
            } catch (FileNotFoundException e) {
                System.out.printf(FILE_NOT_FOUND, fileName);
            }
        }

        @Override
        public boolean hasNext() {
            return reader.hasNextLine();
        }

        @Override
        public String next() {
            if (hasNext()) {
                return reader.nextLine();
            }
            throw new NoSuchElementException();
        }
    }
}
