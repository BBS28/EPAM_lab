package com.epam.lab.shchehlov.task_04.command.Impl;

import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.command.impl.ChangeQuantity;
import com.epam.lab.shchehlov.task_04.repository.impl.BasketRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.BasketService;
import com.epam.lab.shchehlov.task_04.service.impl.BasketServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ChangeQuantityCommandTest {
    private BasketService basketService;

    @Before
    public void before() {
        BasketRepositoryImpl basketRepository = new BasketRepositoryImpl();
        basketRepository.add(10L);
        basketService = new BasketServiceImpl(basketRepository);
    }

    @Test
    public void shouldChangeQuantityToFive() {
        String input = "10" + System.lineSeparator() + "5";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        Command command = new ChangeQuantity(basketService, new Scanner(System.in));
        Integer expectedQuantity = 5;

        command.execute();

        assertEquals(expectedQuantity, basketService.getAll().get(10L));
    }
}
