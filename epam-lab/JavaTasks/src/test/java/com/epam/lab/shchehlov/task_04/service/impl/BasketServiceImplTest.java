package com.epam.lab.shchehlov.task_04.service.impl;

import com.epam.lab.shchehlov.task_04.repository.impl.BasketRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasketServiceImplTest {
    private BasketServiceImpl basketService;

    @Before
    public void Before() {
        BasketRepositoryImpl basketRepository = new BasketRepositoryImpl();
        basketRepository.add(0L);
        basketRepository.add(1L);
        basketRepository.add(2L);
        basketService = new BasketServiceImpl(basketRepository);
    }

    @Test
    public void shouldReturnSizeThreeWhenGetAll() {
        int expectedSize = 3;

        assertEquals(expectedSize, basketService.getAll().size());
    }

    @Test
    public void shouldUpdateAmountToFive() {
        int expectedAmount = 5;
        int actualAmount = 0;
        long id = 1L;

        basketService.updateAmount(id, expectedAmount);
        for (Map.Entry<Long, Integer> entry : basketService.getAll().entrySet()) {
            if (entry.getKey() == id) {
                actualAmount = entry.getValue();
            }
        }

        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    public void shouldReturnSizeZeroAfterDeleteAll() {
        int expectedSize = 0;

        basketService.deleteAll();

        assertEquals(expectedSize, basketService.getAll().size());
    }

    @Test
    public void shouldReturnTrueWhenIsEmptyAfterDeleteAll() {
        basketService.deleteAll();

        assertTrue(basketService.isEmpty());
    }
}
