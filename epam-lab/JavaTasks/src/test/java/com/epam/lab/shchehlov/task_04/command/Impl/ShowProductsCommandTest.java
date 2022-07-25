package com.epam.lab.shchehlov.task_04.command.Impl;

import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.command.impl.ShowProductsCommand;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.service.impl.ProductServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ShowProductsCommandTest {
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
        productRepository.insert(new Drill(1L, "Drill DeWalt 80", 3500, 1200, 3200));
        productRepository.insert(new Drill(2L, "Drill DeWalt 100", 3500, 1200, 3200));
        ProductService productService = new ProductServiceImpl(productRepository);
        command = new ShowProductsCommand(productService);
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
    public void shouldPrintListProductsWhenShowProductsCommand() {
        String expected = "Drill{CordedPowerTool{PowerTool{id=2, name='Drill DeWalt 100', price=3500}power=1200}maxRPM=3200}" +
                System.lineSeparator() +
                "Drill{CordedPowerTool{PowerTool{id=1, name='Drill DeWalt 80', price=3500}power=1200}maxRPM=3200}" +
                System.lineSeparator() +
                "Drill{CordedPowerTool{PowerTool{id=0, name='Drill DeWalt 60', price=3500}power=1200}maxRPM=3200}" +
                System.lineSeparator();

        command.execute();

        Assert.assertEquals(expected, baos.toString());
    }
}
