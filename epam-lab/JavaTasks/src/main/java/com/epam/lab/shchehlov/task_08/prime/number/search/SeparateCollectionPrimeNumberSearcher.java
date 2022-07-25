package com.epam.lab.shchehlov.task_08.prime.number.search;

import com.epam.lab.shchehlov.task_08.constant.Constant;
import com.epam.lab.shchehlov.task_08.prime.number.util.SearchHandler;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Class for searching prime numbers using separate collections.
 *
 * @author B.Shchehlov.
 */
public class SeparateCollectionPrimeNumberSearcher {
    private static final Logger log = Logger.getLogger(SeparateCollectionPrimeNumberSearcher.class.getName());
    private final List<Integer> primeNumberList;

    public SeparateCollectionPrimeNumberSearcher() {
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
        List<FutureTask<List<Integer>>> futureTasks = getFutureList(startNumber, endNumber, threadsAmount, null);
        List<Thread> threadList = getThreadList(futureTasks);
        long startTime = System.nanoTime();

        for (Thread thread : threadList) {
            thread.start();
        }

        for (FutureTask<List<Integer>> future : futureTasks) {
            try {
                primeNumberList.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error(Constant.EXCEPTION_INTERRUPTED, e);
                Thread.currentThread().interrupt();
            }
        }

        long endTime = System.nanoTime();
        SearchHandler.showResult(primeNumberList, startTime, endTime, false, false);
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
        ExecutorService executor = Executors.newFixedThreadPool(threadsAmount);
        List<FutureTask<List<Integer>>> futureTasks = getFutureList(startNumber, endNumber, threadsAmount, executor);
        long startTime = System.nanoTime();

        for (FutureTask<List<Integer>> future : futureTasks) {
            try {
                primeNumberList.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error(Constant.EXCEPTION_INTERRUPTED, e);
                Thread.currentThread().interrupt();
            }
        }
        executor.shutdown();
        long endTime = System.nanoTime();

        SearchHandler.showResult(primeNumberList, startTime, endTime, false, true);

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
     * Returns the result of executing a thread as a list of prime numbers.
     *
     * @param firstNumber first number of search.
     * @param lastNumber  last number of search.
     * @return Callable list of prime numbers.
     */
    private Callable<List<Integer>> threadProcess(int firstNumber, int lastNumber) {
        if (firstNumber == 1) {
            firstNumber++;
        }
        int finalFirstNumber = firstNumber;
        return () -> {
            List<Integer> localPrimeNumbers = new ArrayList<>();
            for (int i = finalFirstNumber; i <= lastNumber; i++) {
                boolean isPrime = true;
                for (int j = 2; j <= (i / 2); j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    localPrimeNumbers.add(i);
                }
            }
            return localPrimeNumbers;
        };
    }

    /**
     * Returns list of future results of searching prime numbers.
     *
     * @param startNumber   first number of search.
     * @param endNumber     last number of search.
     * @param threadsAmount quantity of threads.
     * @param executor      executor service.
     * @return list of future results of searching prime numbers.
     */
    private List<FutureTask<List<Integer>>> getFutureList(int startNumber, int endNumber, int threadsAmount, ExecutorService executor) {
        List<FutureTask<List<Integer>>> futureList = new ArrayList<>();
        int range = SearchHandler.countRange(startNumber, endNumber, threadsAmount);
        int firstNumber = startNumber;
        for (int i = 0; i < threadsAmount; i++) {
            int lastNumber = firstNumber + range;
            if (lastNumber > endNumber) {
                lastNumber = endNumber;
            }
            FutureTask<List<Integer>> futureTask;
            if (executor != null) {
                futureTask = (FutureTask<List<Integer>>) executor.submit(threadProcess(firstNumber, lastNumber));
            } else {
                futureTask = new FutureTask<>(threadProcess(firstNumber, lastNumber));
            }
            futureList.add(futureTask);
            firstNumber = lastNumber + 1;
        }
        return futureList;
    }

    /**
     * Returns a list of threads searching for prime numbers.
     *
     * @param futureList list of future tasks.
     * @return list of threads searching for prime numbers.
     */
    private List<Thread> getThreadList(List<FutureTask<List<Integer>>> futureList) {
        List<Thread> threadList = new ArrayList<>();
        for (FutureTask<List<Integer>> future : futureList) {
            threadList.add(new Thread(future));
        }
        return threadList;
    }
}
