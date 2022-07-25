package com.epam.lab.shchehlov.task_04.service.impl;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_04.repository.impl.AddedProductsRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddedProductRepositoryTest {
    private AddedProductServiceImpl addedProductService;

    @Before
    public void before() {
        AddedProductsRepositoryImpl addedProductRepository = new AddedProductsRepositoryImpl();
        addedProductRepository.insert(2L, new Drill(2L, "Drill DeWalt 60", 3500, 1200, 3200));
        addedProductRepository.insert(5L, new CordedPowerTool(5L, "Milling Cutter DeWalt 300", 1900, 1400));
        addedProductRepository.insert(10L, new CordlessPowerTool(10L, "Screwdriver Bosch 30", 1900, 12, 2));
        addedProductService = new AddedProductServiceImpl(addedProductRepository);
    }

    @Test
    public void shouldReturnSizeThreeWhenGetAll() {
        int expectedSize = 3;

        assertEquals(expectedSize, addedProductService.getAll().size());
    }

    @Test
    public void shouldReturnSizeFiveWhenAddedMore() {
        int expectedSize = 5;

        addedProductService.insert(15L, new Drill(15L, "Drill DeWalt 60", 3500, 1200, 3200));
        addedProductService.insert(18L, new Drill(18L, "Drill DeWalt 80", 3500, 1200, 3200));
        addedProductService.insert(20L, new Drill(20L, "Drill DeWalt 100", 3500, 1200, 3200));

        assertEquals(expectedSize, addedProductService.getAll().size());
    }
}
