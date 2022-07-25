package com.epam.lab.shchehlov.task_04.command.Impl;

import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.command.impl.ShowClosestToDateOrderCommand;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ShowClosestToDateOrderCommandTest {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static ByteArrayOutputStream baos;
    OrderService orderService;
    ProductService productService;

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
        LocalDateTime date1 = LocalDateTime.parse("2020-08-20 14:10:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        LocalDateTime date2 = LocalDateTime.parse("2021-08-20 14:10:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        Hashtable<Long, Integer> basket1 = new Hashtable<>();
        basket1.put(0L, 3);
        Hashtable<Long, Integer> basket2 = new Hashtable<>();
        basket2.put(1L, 1);
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();
        orderRepository.addOne(date1, basket1);
        orderRepository.addOne(date2, basket2);
        productService = new ProductServiceImpl(productRepository);
        orderService = new OrderServiceImpl(orderRepository, new BasketRepositoryImpl());
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
    public void shouldPrintClosestToDateOrder() {
        String input = "2021" + System.lineSeparator() +
                "11" + System.lineSeparator() +
                "22" + System.lineSeparator() +
                "10" + System.lineSeparator() +
                "55" + System.lineSeparator();
        String expected = "Enter the date" + System.lineSeparator() +
                "Enter year (1970 - 2100):" + System.lineSeparator() +
                "Enter month (1 - 12):" + System.lineSeparator() +
                "Enter day (1 - 31):" + System.lineSeparator() +
                "Enter hour (0 - 23):" + System.lineSeparator() +
                "Enter minute (0 - 59):" + System.lineSeparator() +
                "Order closest to the selected date:" + System.lineSeparator() +
                "Drill{CordedPowerTool{PowerTool{id=1, name='Drill DeWalt 100', price=3500}power=1200}maxRPM=3200} - 1 pcs" + System.lineSeparator() +
                "Amount: 3500,00 UAH" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        Command command = new ShowClosestToDateOrderCommand(orderService, productService, new Scanner(System.in));

        command.execute();

        assertEquals(expected, baos.toString());
    }

}
