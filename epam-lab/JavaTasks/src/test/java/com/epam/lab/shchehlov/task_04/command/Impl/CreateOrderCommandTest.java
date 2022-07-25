package com.epam.lab.shchehlov.task_04.command.Impl;

import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.command.impl.CreateOrderCommand;
import com.epam.lab.shchehlov.task_04.repository.impl.BasketRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.OrderRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.BasketService;
import com.epam.lab.shchehlov.task_04.service.OrderService;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.service.impl.BasketServiceImpl;
import com.epam.lab.shchehlov.task_04.service.impl.OrderServiceImpl;
import com.epam.lab.shchehlov.task_04.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;

public class CreateOrderCommandTest {
    private OrderService orderService;
    private BasketService basketService;
    private ProductService productService;

    @Before
    public void before() {
        BasketRepositoryImpl basketRepository = new BasketRepositoryImpl();
        basketRepository.add(0L);
        basketService = new BasketServiceImpl(basketRepository);
        orderService = new OrderServiceImpl(new OrderRepositoryImpl(), basketRepository);
        productService = new ProductServiceImpl(new ProductRepositoryImpl());
        productService.insert(new Drill(0L, "Drill DeWalt 60", 3500, 1200, 3200));
    }

    @Test
    public void shouldAddNewOrder() {
        String input = "2021" + System.lineSeparator() +
                "11" + System.lineSeparator() +
                "22" + System.lineSeparator() +
                "10" + System.lineSeparator() +
                "55" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        Command command = new CreateOrderCommand(basketService, orderService, productService, new Scanner(System.in));
        command.execute();

        assertFalse(orderService.getAll().isEmpty());
    }
}
