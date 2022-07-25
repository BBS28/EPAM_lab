package com.epam.lab.shchehlov.task_06.command;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_06.command.CreateNewProductCommand;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.service.impl.ProductServiceImpl;
import com.epam.lab.shchehlov.task_06.strategy.CreatorStrategy;
import com.epam.lab.shchehlov.task_06.strategy.creator.CreatorContainer;
import com.epam.lab.shchehlov.task_06.strategy.impl.ManualCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class CreateNewProductCommandTest {
    private CreatorStrategy strategy;
    private ProductService productService;

    @Before
    public void before() {
        strategy = new ManualCreator();
        productService = new ProductServiceImpl(new ProductRepositoryImpl());
    }

    @Test
    public void shouldCreateAndAddNewPowerToolToProductService() {
        String input = "0" + System.lineSeparator() +
                "tool 123456" + System.lineSeparator() +
                "1000" + System.lineSeparator();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        Scanner scanner = new Scanner(System.in);
        CreatorContainer creatorContainer = new CreatorContainer(strategy, scanner);
        CreateNewProductCommand command = new CreateNewProductCommand(productService, creatorContainer, scanner);
        PowerTool expectedTool = new PowerTool(0L, "tool 123456", 1000);

        command.execute();

        Assert.assertEquals(expectedTool, productService.getOne(0L));
    }
}
