package com.epam.lab.shchehlov.task_08.prime.number.search;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SeparateCollectionPrimeNumberSearcherTest {
    private SeparateCollectionPrimeNumberSearcher searcher;

    @Before
    public void before() {
        searcher = new SeparateCollectionPrimeNumberSearcher();
    }

    @Test
    public void shouldReturnArrayListWithExpectedSizeAfterSearch() {
        searcher.search(10, 100, 5);

        int expectedSize = 21;
        int actualSize = searcher.getPrimeNumberList().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void shouldReturnArrayListWithExpectedSizeAfterExecutorSearch() {
        searcher.executorSearch(10, 100, 5);

        int expectedSize = 21;
        int actualSize = searcher.getPrimeNumberList().size();

        assertEquals(expectedSize, actualSize);
    }
}
