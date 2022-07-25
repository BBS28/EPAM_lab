package com.epam.lab.shchehlov.task_04.command.Impl;

import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.command.impl.ShowAllOrdersCommand;
import com.epam.lab.shchehlov.task_04.repository.impl.BasketRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.OrderRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.OrderService;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.service.impl.OrderServiceImpl;
import com.epam.lab.shchehlov.task_04.service.impl.ProductServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

public class ShowAllOrdersCommandTest {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

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
        LocalDateTime date = LocalDateTime.parse("2020-08-20 14:10:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        Hashtable<Long, Integer> basket = new Hashtable<>();
        basket.put(0L, 3);
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();
        orderRepository.addOne(date, basket);
        ProductService productService = new ProductServiceImpl(productRepository);
        OrderService orderService = new OrderServiceImpl(orderRepository, new BasketRepositoryImpl());
        command = new ShowAllOrdersCommand(orderService, productService);
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
    public void shouldPrintAllOrders() {
        String expected = "Date: 2020-08-20 14:10:00" + System.lineSeparator() +
                "Products:" + System.lineSeparator() +
                "Drill{CordedPowerTool{PowerTool{id=0, name='Drill DeWalt 60', price=3500}power=1200}maxRPM=3200} - 3 pcs" + System.lineSeparator() +
                "Amount: 10500,00 UAH" + System.lineSeparator() +
                "--------------------------------------------------------" + System.lineSeparator();

        command.execute();

        assertEquals(expected, baos.toString());
    }
}
