package com.epam.lab.shchehlov.task_06.util;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.CordlessPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.service.impl.ProductServiceImpl;
import com.epam.lab.shchehlov.task_06.util.ProductSerializer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ProductSerializerTest {
    private static final long DEFAULT_ID = 1L;

    private ProductService productService;
    private String filePath;

    @Before
    public void before() {
        PowerTool[] tools = new PowerTool[]{
                new Drill(DEFAULT_ID, "Drill Bosch 10", 2098, 1000, 2400),
                new Drill(DEFAULT_ID, "Drill Bosch 20", 2598, 1400, 2800),
                new Drill(DEFAULT_ID, "Drill Makita 30", 3000, 1200, 2900),
                new Drill(DEFAULT_ID, "Drill Makita 40", 1500, 1000, 2000),
                new Drill(DEFAULT_ID, "Drill DeWalt 50", 1098, 800, 1800),
                new Drill(DEFAULT_ID, "Drill DeWalt 60", 3500, 1200, 3200),
                new CordedPowerTool(DEFAULT_ID, "Jigsaw Bosch 100", 1000, 800),
                new CordedPowerTool(DEFAULT_ID, "Jigsaw Makita 200", 1500, 1200),
                new CordedPowerTool(DEFAULT_ID, "Milling Cutter DeWalt 300", 1900, 1400),
                new CordlessPowerTool(DEFAULT_ID, "Screwdriver Bosch 30", 1900, 12, 2)
        };
        productService = new ProductServiceImpl(new ProductRepositoryImpl(tools));
        filePath = "src\\test\\resources\\task_04\\serialized_array.srl";
    }

    @Test
    public void shouldReturnSameArrayAfterSerialization() {
        PowerTool[] expectedArray = new PowerTool[productService.getAll().size()];
        productService.getAll().values().toArray(expectedArray);

        ProductSerializer.saveProducts(productService, filePath);
        PowerTool[] actualArray = ProductSerializer.loadProducts(filePath);

        assertArrayEquals(expectedArray, actualArray);
    }
}
