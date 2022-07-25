package com.epam.lab.shchehlov.task_08.prime.number.search;

import com.epam.lab.shchehlov.task_08.constant.Constant;
import com.epam.lab.shchehlov.task_08.prime.number.util.SearchHandler;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Class for searching prime numbers using a common collection.
 *
 * @author B.Shchehlov.
 */
public class CommonCollectionPrimeNumberSearcher {
    private static final Logger log = Logger.getLogger(CommonCollectionPrimeNumberSearcher.class.getName());
    private final List<Integer> primeNumberList;
    private final Object monitor = new Object();

    public CommonCollectionPrimeNumberSearcher() {
        this.primeNumberList = new ArrayList<>();
    }

    /**
     * Manages the creation and destroying of threads for search prime numbers.
     *
     * @param startNumber   first number of search.
     * @param endNumber     last number of search.
     * @param threadsAmount quantity of threads.
     */
    public void search(int startNumber, int endNumber, int threadsAmount) {
        primeNumberList.clear();
        List<Thread> threadList = getThreadList(startNumber, endNumber, threadsAmount);
        long startTime = System.nanoTime();

        for (Thread thread : threadList) {
            try {
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                log.error(Constant.EXCEPTION_INTERRUPTED, e);
                Thread.currentThread().interrupt();
            }
        }
        long endTime = System.nanoTime();

        SearchHandler.showResult(primeNumberList, startTime, endTime, true, false);
    }

    /**
     * Searches prime numbers using executors.
     *
     * @param startNumber   first number of search.
     * @param endNumber     last number of search.
     * @param threadsAmount quantity of threads.
     */
    public void executorSearch(int startNumber, int endNumber, int threadsAmount) {
        primeNumberList.clear();
        ExecutorService executorService = Executors.newFixedThreadPool(threadsAmount);
        int range = SearchHandler.countRange(startNumber, endNumber, threadsAmount);
        int firstNumber = startNumber;
        long startTime = System.nanoTime();

        try {
            for (int i = 0; i < threadsAmount; i++) {
                int lastNumber = firstNumber + range;

                if (lastNumber > endNumber) {
                    lastNumber = endNumber;
                }

                executorService.execute(threadProcess(firstNumber, lastNumber));
                firstNumber = lastNumber + 1;
            }

            executorService.shutdown();
            executorService.awaitTermination(Constant.TIMEOUT_MINUTES, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            log.error(Constant.EXCEPTION_INTERRUPTED, e);
            Thread.currentThread().interrupt();
        }

        long endTime = System.nanoTime();

        SearchHandler.showResult(primeNumberList, startTime, endTime, true, true);
    }

    /**
     * Returns list of prime numbers.
     *
     * @return list of prime numbers.
     */
    public List<Integer> getPrimeNumberList() {
        return primeNumberList;
    }

    /**
     * Returns list of threads responsible for finding numbers in their range.
     *
     * @param startNumber   first number of search.
     * @param endNumber     last number of search.
     * @param threadsAmount quantity of threads.
     * @return list of threads.
     */
    private List<Thread> getThreadList(int startNumber, int endNumber, int threadsAmount) {
        List<Thread> threadList = new ArrayList<>();
        int range = SearchHandler.countRange(startNumber, endNumber, threadsAmount);
        int firstNumber = startNumber;
        for (int i = 0; i < threadsAmount; i++) {
            int lastNumber = firstNumber + range;
            if (lastNumber > endNumber) {
                lastNumber = endNumber;
            }
            threadList.add(threadProcess(firstNumber, lastNumber));
            firstNumber = lastNumber + 1;
        }
        return threadList;
    }

    /**
     * Thread of searching prime numbers in a given range.
     *
     * @param firstNumber first number of search.
     * @param lastNumber  last number of search.
     * @return quantity of threads.
     */
    private Thread threadProcess(int firstNumber, int lastNumber) {
        if (firstNumber == 1) {
            firstNumber++;
        }
        int finalFirstNumber = firstNumber;
        return new Thread(() -> {
            for (int i = finalFirstNumber; i <= lastNumber; i++) {
                boolean isPrime = true;
                for (int j = 2; j <= (i / 2); j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    synchronized (monitor) {
                        primeNumberList.add(i);
                    }
                }
            }
        });
    }
}
