package com.epam.lab.shchehlov.task_08.prime.number.util;

import com.epam.lab.shchehlov.task_08.constant.Constant;

import java.util.Collections;
import java.util.List;

public class SearchHandler {

    /**
     * Prints the result of searching for prime numbers to the console.
     *
     * @param primeNumberList    list of prime numbers.
     * @param startTime          search start time.
     * @param endTime            search end time.
     * @param isCommonCollection true if the search was performed from one collection.
     * @param isExecutor         true if the search was performed with executors.
     */
    public static void showResult(List<Integer> primeNumberList, long startTime, long endTime, boolean isCommonCollection, boolean isExecutor) {
        long processTime = endTime - startTime;
        double processInMs = processTime / Constant.COEFFICIENT_NS_TO_MS;
        String collectionAmount = Constant.SEPARATE;
        String executorPresence = Constant.WITHOUT;

        if (isCommonCollection) {
            collectionAmount = Constant.COMMON;
        }
        if (isExecutor) {
            executorPresence = Constant.WITH;
        }

        Collections.sort(primeNumberList);
        System.out.printf(Constant.FORMAT_RESULT_NUMBERS, collectionAmount, executorPresence, processInMs, processTime, primeNumberList);
    }

    /**
     * Returns range of search.
     *
     * @param startNumber   first number of search.
     * @param endNumber     last number of search.
     * @param threadsAmount quantity of threads.
     * @return range of search.
     */
    public static int countRange(int startNumber, int endNumber, int threadsAmount) {
        return (endNumber - startNumber) / threadsAmount;
    }
}
