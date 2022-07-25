package com.epam.lab.shchehlov.task_07.proxy.factory;

import com.epam.lab.shchehlov.task_07.proxy.domain.Tool;
import com.epam.lab.shchehlov.task_07.proxy.domain.impl.ToolImpl;
import com.epam.lab.shchehlov.task_07.proxy.factory.impl.MapImplementationToolProxyFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MapImplementationToolProxyFactoryTest {
    private static ByteArrayOutputStream byteArrayOutputStream;

    MapImplementationToolProxyFactory mapImplementationToolProxyFactory;
    Tool tool;
    Tool tool2;

    @BeforeClass
    public static void beforeClass() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Before
    public void before() {
        mapImplementationToolProxyFactory = new MapImplementationToolProxyFactory();
        tool = mapImplementationToolProxyFactory.createProxy(new ToolImpl(10L, "Tool 123", 2000, 1000));
        tool2 = mapImplementationToolProxyFactory.createProxy(new ToolImpl(10L, "Tool 123", 1000));
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
    public void shouldReturnProductWithNewPrice() {
        long expectedId = 20L;
        int expectedPrice = 2500;
        int expectedPower = 2500;

        tool.setId(expectedId);
        tool.setPrice(expectedPrice);
        tool.setPower(expectedPower);

        assertEquals(expectedId, tool.getId());
        assertEquals(expectedPrice, tool.getPrice());
        assertEquals(expectedPower, tool.getPower());
    }

    @Test
    public void shouldReturnProductWithZeroPrice() {
        long expectedId = 20L;
        int expectedPrice = 0;
        int expectedPower = 2500;

        tool2.setId(expectedId);
        tool2.setPower(expectedPower);

        assertEquals(expectedId, tool2.getId());
        assertEquals(expectedPrice, tool2.getPrice());
        assertEquals(expectedPower, tool2.getPower());
    }
}
