package com.epam.lab.shchehlov.task_04.command.Impl;

import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_04.command.Command;
import com.epam.lab.shchehlov.task_04.command.impl.AddToBasketCommand;
import com.epam.lab.shchehlov.task_04.repository.impl.AddedProductsRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.BasketRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.AddedProductsService;
import com.epam.lab.shchehlov.task_04.service.BasketService;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.service.impl.AddedProductServiceImpl;
import com.epam.lab.shchehlov.task_04.service.impl.BasketServiceImpl;
import com.epam.lab.shchehlov.task_04.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;

public class AddToBasketCommandTest {
    private BasketService basketService;
    private ProductService productService;
    private AddedProductsService addedProductsService;

    @Before
    public void before() {
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.insert(new Drill(0L, "Drill DeWalt 60", 3500, 1200, 3200));
        productService = new ProductServiceImpl(productRepository);
        basketService = new BasketServiceImpl(new BasketRepositoryImpl());
        addedProductsService = new AddedProductServiceImpl(new AddedProductsRepositoryImpl());
    }

    @Test
    public void shouldReturnFalseBasketIsEmptyAfterAddedProduct() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("0".getBytes());
        System.setIn(byteArrayInputStream);
        Command command = new AddToBasketCommand(productService, basketService, addedProductsService, new Scanner(System.in));

        command.execute();

        assertFalse(basketService.getAll().isEmpty());
    }
}
