package com.epam.lab.shchehlov.task_08.sequence.search;

import com.epam.lab.shchehlov.task_08.constant.Constant;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that does the work of the main thread to find the longest repeating sequence.
 *
 * @author B.Shchehlov.
 */
public class LongestSequenceProcessor {
    private static final Logger log = Logger.getLogger(LongestSequenceProcessor.class);

    private final Scanner scanner;
    private final ReentrantLock mainLock;
    private final LongestSequenceSearcher longestSequenceSearcher;

    private File file;
    private int currentSize;

    public LongestSequenceProcessor(Scanner scanner) {
        this.scanner = scanner;
        this.mainLock = new ReentrantLock();
        this.longestSequenceSearcher = new LongestSequenceSearcher(mainLock);
    }

    /**
     * Executes the main loop of the application from starting an additional thread and reading the file path to
     * outputting the results and ending the program.
     */
    public void process() {
        currentSize = 0;
        mainLock.lock();
        longestSequenceSearcher.start();

        do {
            do {
                System.out.println(Constant.ENTER_PATH);
                String input = scanner.nextLine();

                if (input.equals(Constant.INPUT_ZERO)) {
                    longestSequenceSearcher.interrupt();
                    return;
                }
                file = new File(input);
            } while (!file.isFile());

            longestSequenceSearcher.setByteList(createByteList());
            mainLock.unlock();
            showProcessInfo();
        } while (true);
    }

    /**
     * Gets a list of bytes from a file.
     *
     * @return list of bytes.
     */
    private List<Byte> createByteList() {
        List<Byte> list = new ArrayList<>();
        try {
            byte[] array = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

            for (byte b : array) {
                list.add(b);
            }

        } catch (IOException e) {
            log.error(Constant.EXCEPTION_CONVERT, e);
        }
        return list;
    }

    /**
     * Displays intermediate and final search results to the console.
     */
    private void showProcessInfo() {
        while (mainLock.tryLock()) {
            if (longestSequenceSearcher.getSequenceSize() != currentSize) {
                System.out.printf(Constant.FORMAT_CURRENT_RESULT, longestSequenceSearcher.getSequenceSize());
                currentSize = longestSequenceSearcher.getSequenceSize();
            }

            if (longestSequenceSearcher.isComplete()) {
                System.out.printf(Constant.FORMAT_RESULT_SEQUENCE,
                        longestSequenceSearcher.getSequenceSize(),
                        longestSequenceSearcher.getFirstIndex(),
                        longestSequenceSearcher.getSecondIndex());

                longestSequenceSearcher.clearResult();
                currentSize = 0;
                return;
            } else {
                mainLock.unlock();
            }
        }
    }
}
