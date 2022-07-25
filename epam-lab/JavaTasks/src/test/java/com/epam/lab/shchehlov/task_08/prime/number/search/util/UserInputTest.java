package com.epam.lab.shchehlov.task_08.prime.number.search.util;

import com.epam.lab.shchehlov.task_08.prime.number.util.UserInput;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class UserInputTest {

    @Test
    public void shouldReturnInputtedNumberWhenStartNumberInput() {
        String input = "10";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int expected = 10;

        assertEquals(expected, UserInput.startNumberInput(new Scanner(System.in)));
    }

    @Test
    public void shouldReturnInputtedNumberWhenEndNumberInput() {
        String input = "1000";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int expected = 1000;

        assertEquals(expected, UserInput.endNumberInput(new Scanner(System.in), 10));
    }

    @Test
    public void shouldReturnInputtedNumberWhenThreadNumberInput() {
        String input = "5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int expected = 5;

        assertEquals(expected, UserInput.threadNumberInput(new Scanner(System.in), 10, 1000));
    }
}
