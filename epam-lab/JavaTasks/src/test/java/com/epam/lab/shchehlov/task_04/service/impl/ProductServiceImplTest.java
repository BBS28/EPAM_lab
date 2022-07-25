package com.epam.lab.shchehlov.task_04.service.impl;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductServiceImplTest {
    private ProductServiceImpl productService;

    @Before
    public void before() {
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.insert(new Drill(0L, "Drill DeWalt 60", 3500, 1200, 3200));
        productRepository.insert(new CordedPowerTool(1L, "Milling Cutter DeWalt 300", 1900, 1400));
        productRepository.insert(new CordlessPowerTool(2L, "Screwdriver Bosch 30", 1900, 12, 2));
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void shouldReturnSizeThreeWhenGetAll() {
        int expectedSize = 3;

        assertEquals(expectedSize, productService.getAll().size());
    }

    @Test
    public void shouldReturnSizeTwoAfterDelete() {
        int expectedSize = 2;

        productService.delete(1L);

        assertEquals(expectedSize, productService.getAll().size());
    }

    @Test
    public void shouldReturnRightElementWhenGetOne() {
        PowerTool expectedTool = new CordedPowerTool(1L, "Milling Cutter DeWalt 300", 1900, 1400);

        assertEquals(expectedTool, productService.getOne(1L));
    }
}
