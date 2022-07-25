package com.epam.lab.shchehlov.task_04.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.epam.lab.shchehlov.task_04.util.DateTimeValidator.input;
import static org.junit.Assert.assertEquals;

public class DateTimeValidatorTest {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Test
    public void shouldReturnRightLocalDateTime() {
        LocalDateTime expectedDate = LocalDateTime.parse("2021-11-22 10:50:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        String input = "2021" + System.lineSeparator() +
                "11" + System.lineSeparator() +
                "22" + System.lineSeparator() +
                "10" + System.lineSeparator() +
                "50" + System.lineSeparator();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(byteArrayInputStream);

        LocalDateTime date = input(scanner);

        assertEquals(expectedDate, date);
    }
}
