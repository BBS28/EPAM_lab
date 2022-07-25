package com.epam.lab.shchehlov.task_04.repository.impl;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductRepositoryImplTest {
    private ProductRepositoryImpl repository;

    @Before
    public void Before() {
        repository = new ProductRepositoryImpl();
        repository.insert(new Drill(0L, "Drill DeWalt 60", 3500, 1200, 3200));
        repository.insert(new CordedPowerTool(1L, "Milling Cutter DeWalt 300", 1900, 1400));
        repository.insert(new CordlessPowerTool(2L, "Screwdriver Bosch 30", 1900, 12, 2));
    }

    @Test
    public void shouldReturnHashTableWithSizeThree() {
        int expectedSize = 3;

        assertEquals(expectedSize, repository.getAll().size());
    }

    @Test
    public void shouldReturnChangedPriceWhenUpdate() {
        int expectedPrice = 3000;
        Drill drill = new Drill(0L, "Drill DeWalt 60", 3000, 1500, 2600);

        repository.update(drill);

        assertEquals(expectedPrice, repository.getOne(0L).getPrice());
    }

    @Test
    public void shouldReturnHashTableWithSizeTwoAfterDelete() {
        int expectedSize = 2;

        repository.delete(1L);

        assertEquals(expectedSize, repository.getAll().size());
    }

    @Test
    public void shouldReturnRightElementWhenGetOne() {
        PowerTool expectedTool = new CordedPowerTool(1L, "Milling Cutter DeWalt 300", 1900, 1400);

        assertEquals(expectedTool, repository.getOne(1L));
    }
}
