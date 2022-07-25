package com.epam.lab.shchehlov.task_04.repository.impl;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class OrderRepositoryImplTest {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private OrderRepositoryImpl orderRepository;

    @Before
    public void before() {
        TreeMap<LocalDateTime, Hashtable<Long, Integer>> orders = new TreeMap<>();

        LocalDateTime date1 = LocalDateTime.parse("2020-08-20 14:10:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        LocalDateTime date2 = LocalDateTime.parse("2021-10-04 11:50:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        LocalDateTime date3 = LocalDateTime.parse("2021-11-01 12:50:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        Hashtable<Long, Integer> basket1 = new Hashtable<>();
        basket1.put(1L, 2);
        basket1.put(3L, 1);

        Hashtable<Long, Integer> basket2 = new Hashtable<>();
        basket2.put(0L, 1);
        basket2.put(5L, 2);
        basket2.put(10L, 5);

        Hashtable<Long, Integer> basket3 = new Hashtable<>();
        basket3.put(2L, 2);

        orders.put(date1, basket1);
        orders.put(date2, basket2);
        orders.put(date3, basket3);

        orderRepository = new OrderRepositoryImpl(orders);
    }

    @Test
    public void shouldReturnSizeFourWhenAddOne() {
        int expectedSize = 4;
        LocalDateTime newDate = LocalDateTime.parse("2021-11-21 13:10:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        Hashtable<Long, Integer> newBasket = new Hashtable<>();
        newBasket.put(3L, 1);

        orderRepository.addOne(newDate, newBasket);

        assertEquals(expectedSize, orderRepository.getAll().size());
    }

    @Test
    public void shouldReturnSizeThreeWhenGetByDate() {
        int expectedSize = 3;
        LocalDateTime date = LocalDateTime.parse("2021-10-04 11:50:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        int actualSize = orderRepository.getByDate(date).size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void shouldReturnRightDateWhenGetClosestDate() {
        Hashtable<Long, Integer> expectedBasket = new Hashtable<>();
        expectedBasket.put(1L, 2);
        expectedBasket.put(3L, 1);
        LocalDateTime date = LocalDateTime.parse("2019-01-20 14:10:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        assertEquals(expectedBasket, orderRepository.getByClosestDate(date));
    }

    @Test
    public void shouldReturnWithSizeThreeWhenGetAll() {
        int expectedSize = 3;

        assertEquals(expectedSize, orderRepository.getAll().size());
    }

    @Test
    public void shouldReturnWithSizeOneWhenGetAllWithParameters() {
        int expectedSize = 1;
        LocalDateTime date1 = LocalDateTime.parse("2021-09-04 11:50:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        LocalDateTime date2 = LocalDateTime.parse("2021-10-09 11:50:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

        assertEquals(expectedSize, orderRepository.getOrdersForPeriod(date1, date2).size());
    }
}
