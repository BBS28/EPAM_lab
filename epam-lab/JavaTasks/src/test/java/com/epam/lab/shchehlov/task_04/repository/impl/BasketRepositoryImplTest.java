package com.epam.lab.shchehlov.task_04.repository.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasketRepositoryImplTest {
    private BasketRepositoryImpl basket;

    @Before
    public void Before() {
        basket = new BasketRepositoryImpl();
        basket.add(0L);
        basket.add(1L);
        basket.add(2L);
    }

    @Test
    public void shouldReturnSizeThreeWhenGetAll() {
        int expectedSize = 3;

        assertEquals(expectedSize, basket.getAll().size());
    }

    @Test
    public void shouldUpdateAmountToFive() {
        int expectedAmount = 5;
        int actualAmount = 0;
        long id = 1L;

        basket.updateAmount(id, expectedAmount);
        for (Map.Entry<Long, Integer> entry : basket.getAll().entrySet()) {
            if (entry.getKey() == id) {
                actualAmount = entry.getValue();
            }
        }

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    public void shouldReturnSizeTwoAfterDeleteOne() {
        int expectedSize = 2;
        long id = 1L;

        basket.deleteOne(id);

        assertEquals(expectedSize, basket.getAll().size());
    }

    @Test
    public void shouldReturnSizeZeroAfterDeleteAll() {
        int expectedSize = 0;

        basket.deleteAll();

        assertEquals(expectedSize, basket.getAll().size());
    }

    @Test
    public void shouldReturnTrueWhenIsEmptyAfterDeleteAll() {
        basket.deleteAll();

        assertTrue(basket.isEmpty());
    }
}
