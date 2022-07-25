package com.epam.lab.shchehlov.task_04.util;

import com.epam.lab.shchehlov.task_01.entity.CordedPowerTool;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.ProductService;
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
import java.util.TreeMap;

import static com.epam.lab.shchehlov.task_04.util.OrdersPrinter.printOneOrder;
import static com.epam.lab.shchehlov.task_04.util.OrdersPrinter.printOrders;
import static org.junit.Assert.assertEquals;

public class OrdersPrinterTest {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private ProductService productService;
    private TreeMap<LocalDateTime, Hashtable<Long, Integer>> orders;
    private Hashtable<Long, Integer> order;
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
        productRepository.insert(new CordedPowerTool(1L, "Milling Cutter DeWalt 300", 1900, 1400));

        productService = new ProductServiceImpl(productRepository);

        order = new Hashtable<>();
        order.put(0L, 3);

        LocalDateTime date1 = LocalDateTime.parse("2020-08-20 14:10:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        LocalDateTime date2 = LocalDateTime.parse("2021-10-04 11:50:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        orders = new TreeMap<>();
        orders.put(date1, order);
        orders.put(date2, order);
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
    public void shouldPrintOrderInfo() {
        printOneOrder(productService, order);
        final String expected = "Drill{CordedPowerTool{PowerTool{id=0, name='Drill DeWalt 60', price=3500}power=1200}maxRPM=3200} - 3 pcs" + System.lineSeparator() +
                "Amount: 10500,00 UAH" + System.lineSeparator();
        final String actual = baos.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldPrintSeveralOrdersInfo() {
        printOrders(productService, orders);
        final String expected = "Date: 2020-08-20 14:10:00" + System.lineSeparator() +
                "Products:" + System.lineSeparator() +
                "Drill{CordedPowerTool{PowerTool{id=0, name='Drill DeWalt 60', price=3500}power=1200}maxRPM=3200} - 3 pcs" + System.lineSeparator() +
                "Amount: 10500,00 UAH" + System.lineSeparator() +
                "--------------------------------------------------------" + System.lineSeparator() +
                "Date: 2021-10-04 11:50:00" + System.lineSeparator() +
                "Products:" + System.lineSeparator() +
                "Drill{CordedPowerTool{PowerTool{id=0, name='Drill DeWalt 60', price=3500}power=1200}maxRPM=3200} - 3 pcs" + System.lineSeparator() +
                "Amount: 10500,00 UAH" + System.lineSeparator() +
                "--------------------------------------------------------" + System.lineSeparator();
        final String actual = baos.toString();

        assertEquals(expected, actual);
    }
}
