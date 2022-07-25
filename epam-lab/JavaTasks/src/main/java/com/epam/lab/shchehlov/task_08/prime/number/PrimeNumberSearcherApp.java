package com.epam.lab.shchehlov.task_08.prime.number;

import com.epam.lab.shchehlov.task_08.prime.number.search.CommonCollectionPrimeNumberSearcher;
import com.epam.lab.shchehlov.task_08.prime.number.search.SeparateCollectionPrimeNumberSearcher;
import com.epam.lab.shchehlov.task_08.prime.number.util.UserInput;

import java.util.Scanner;

/**
 * The class that launches the execution of the application to search prime numbers.
 *
 * @author B.Shchehlov.
 */
public class PrimeNumberSearcherApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startNumber = UserInput.startNumberInput(scanner);
        int endNumber = UserInput.endNumberInput(scanner, startNumber);
        int threadsNumber = UserInput.threadNumberInput(scanner, startNumber, endNumber);

        CommonCollectionPrimeNumberSearcher searcher = new CommonCollectionPrimeNumberSearcher();
        searcher.search(startNumber, endNumber, threadsNumber);
        searcher.executorSearch(startNumber, endNumber, threadsNumber);

        SeparateCollectionPrimeNumberSearcher separateSearcher = new SeparateCollectionPrimeNumberSearcher();
        separateSearcher.search(startNumber, endNumber, threadsNumber);
        separateSearcher.executorSearch(startNumber, endNumber, threadsNumber);
    }
}
