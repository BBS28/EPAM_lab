package com.epam.lab.shchehlov.task_08.sequence;

import com.epam.lab.shchehlov.task_08.sequence.search.LongestSequenceProcessor;

import java.util.Scanner;

/**
 * The class that launches the execution of the application to find the most repeating sequence.
 *
 * @author B.Shchehlov.
 */
public class LongestSequenceApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LongestSequenceProcessor longestSequenceProcessor = new LongestSequenceProcessor(scanner);
        longestSequenceProcessor.process();

//        src\main\resources\task_08\byte_sequence_01.txt
//        src\main\resources\task_08\byte_sequence_02.txt
    }
}
