package com.epam.lab.shchehlov.task_07.proxy.factory;

import com.epam.lab.shchehlov.task_07.proxy.domain.Tool;
import com.epam.lab.shchehlov.task_07.proxy.domain.impl.ToolImpl;
import com.epam.lab.shchehlov.task_07.proxy.exception.UnacceptableMethodException;
import com.epam.lab.shchehlov.task_07.proxy.factory.impl.UnmodifiableToolProxyFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class UnmodifiableToolProxyFactoryTest {
    private static ByteArrayOutputStream byteArrayOutputStream;

    UnmodifiableToolProxyFactory unmodifiableToolProxyFactory;
    Tool tool;

    @BeforeClass
    public static void beforeClass() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Before
    public void before() {
        unmodifiableToolProxyFactory = new UnmodifiableToolProxyFactory();
        tool = unmodifiableToolProxyFactory.createProxy(new ToolImpl(10L, "name 123", 2000, 1000));
    }

    @After
    public void after() {
        byteArrayOutputStream.reset();
    }

    @AfterClass
    public static void afterClass() {
        System.setOut(System.out);
    }

    @Test(expected = UnacceptableMethodException.class)
    public void shouldThrowUnacceptableMethodException() {
        tool.setName("newName");
    }

    @Test
    public void shouldReturnToolName () {
        String expectedOutput = "name 123";

        assertEquals(expectedOutput, tool.getName());
    }
}
