package com.epam.lab.shchehlov.task_07.shop.reflection.command;

import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.service.impl.ProductServiceImpl;
import com.epam.lab.shchehlov.task_07.shop.reflection.creator.ProductNameContainer;
import com.epam.lab.shchehlov.task_07.shop.reflection.init.Initializer;
import com.epam.lab.shchehlov.task_07.shop.reflection.init.impl.AutoInitializer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class CreateProductReflectionCommandTest {
    private static final String ENGLISH = "en";
    private static final String LOCALE = "locale";

    private static ByteArrayOutputStream byteArrayOutputStream;

    ProductService productService;
    Initializer initializer;
    ResourceBundle resourceBundle;
    ProductNameContainer productNameContainer;

    @BeforeClass
    public static void beforeClass() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Before
    public void before() {
        productService = new ProductServiceImpl(new ProductRepositoryImpl());
        initializer = new AutoInitializer();
        productNameContainer = new ProductNameContainer();
        resourceBundle = ResourceBundle.getBundle(LOCALE, new Locale(ENGLISH));
    }

    @After
    public void after() {
        byteArrayOutputStream.reset();
    }

    @AfterClass
    public static void afterClass() {
        System.setOut(System.out);
    }

    @Test
    public void shouldCreateNewProductAndAddItToProductService() {
        String input = "0" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CreateProductReflectionCommand command = new CreateProductReflectionCommand(
                productService,
                initializer,
                productNameContainer,
                resourceBundle,
                new Scanner(System.in));
        command.execute();

        assertEquals(1, productService.getAll().size());
    }

    @Test
    public void shouldCreateNewProductAndAddItToProductServiceAfterTwoWrongInputs() {
        String input = "10" + System.lineSeparator() +
                "wrong" + System.lineSeparator() +
                "3" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CreateProductReflectionCommand command = new CreateProductReflectionCommand(
                productService,
                initializer,
                productNameContainer,
                resourceBundle,
                new Scanner(System.in));
        command.execute();

        assertEquals(1, productService.getAll().size());
    }

    @Test
    public void shouldPrintCommandName() {
        String expectedOutput = "Create new product with reflection";

        CreateProductReflectionCommand command = new CreateProductReflectionCommand(
                productService,
                initializer,
                productNameContainer,
                resourceBundle,
                new Scanner(System.in));

        assertEquals(expectedOutput, command.getDescription());
    }
}
