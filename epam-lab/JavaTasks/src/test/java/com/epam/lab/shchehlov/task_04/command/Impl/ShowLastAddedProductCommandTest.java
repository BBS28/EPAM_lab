package com.epam.lab.shchehlov.task_04.command.Impl;

import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.command.impl.ShowLastAddedProductsCommand;
import com.epam.lab.shchehlov.task_04.repository.impl.AddedProductsRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.AddedProductsService;
import com.epam.lab.shchehlov.task_04.service.impl.AddedProductServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ShowLastAddedProductCommandTest {
    private Command command;
    private static ByteArrayOutputStream baos;

    @BeforeClass
    public static void beforeClass() {
        baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    }

    @Before
    public void before() {
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.insert(new Drill(0L, "Drill DeWalt 60", 3500, 1200, 3200));
        productRepository.insert(new Drill(1L, "Drill DeWalt 100", 3500, 1200, 3200));
        AddedProductsRepositoryImpl addedProductsRepository = new AddedProductsRepositoryImpl();
        addedProductsRepository.insert(0L, productRepository.getOne(0L));
        addedProductsRepository.insert(1L, productRepository.getOne(1L));
        AddedProductsService addedProductsService = new AddedProductServiceImpl(addedProductsRepository);
        command = new ShowLastAddedProductsCommand(addedProductsService);
    }

    @After
    public void after() {
        baos.reset();
    }

    @AfterClass
    public static void afterClass() {
        System.setOut(System.out);
    }

    @Test
    public void shouldPrintTwoLastAddedProducts() {
        String expected = "Drill{CordedPowerTool{PowerTool{id=0, name='Drill DeWalt 60', price=3500}power=1200}maxRPM=3200}" + System.lineSeparator() +
                "Drill{CordedPowerTool{PowerTool{id=1, name='Drill DeWalt 100', price=3500}power=1200}maxRPM=3200}" + System.lineSeparator();

        command.execute();

        assertEquals(expected, baos.toString());
    }
}
