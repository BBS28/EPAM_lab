package com.epam.lab.shchehlov.task_04.command.Impl;

import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.command.impl.ShowBasketCommand;
import com.epam.lab.shchehlov.task_04.repository.impl.AddedProductsRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.BasketRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.AddedProductsService;
import com.epam.lab.shchehlov.task_04.service.BasketService;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.service.impl.AddedProductServiceImpl;
import com.epam.lab.shchehlov.task_04.service.impl.BasketServiceImpl;
import com.epam.lab.shchehlov.task_04.service.impl.ProductServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ShowBasketCommandTest {
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

        ProductService productService = new ProductServiceImpl(productRepository);
        BasketRepositoryImpl basketRepository = new BasketRepositoryImpl();
        basketRepository.add(0L);
        basketRepository.add(1L);

        BasketService basketService = new BasketServiceImpl(basketRepository);
        AddedProductsService addedProductsService = new AddedProductServiceImpl(new AddedProductsRepositoryImpl());
        command = new ShowBasketCommand(basketService, productService);
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
    public void shouldPrintBasket() {
        String expected = "(id-1) Drill DeWalt 100 - 1 pcs" + System.lineSeparator() +
                "(id-0) Drill DeWalt 60 - 1 pcs" + System.lineSeparator() +
                "Total value : 7000,00 UAH" + System.lineSeparator();

        command.execute();

        assertEquals(expected, baos.toString());
    }
}
