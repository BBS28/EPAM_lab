package com.epam.lab.shchehlov.task_04.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Class for getting date and time entered from console
 *
 * @author B.Shchehlov
 */
public class DateTimeValidator {
    private static final String MESSAGE_ENTER_DATE = "Enter %s (%d - %d):%n";
    private static final String ERROR_ENTER_MESSAGE = "Please enter a numerical value!";
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";
    private static final String HOUR = "hour";
    private static final String MINUTE = "minute";
    private static final String TWO_DIGIT_FORMAT = "%02d";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String WARN_MESSAGE = "Please enter a number within %d - %d%n";
    private static final char DELIMITER_DATE = '-';
    private static final char DELIMITER_TIME = ':';
    private static final char DELIMITER_DATE_TIME = ' ';
    private static final int DEFAULT_VALUE = -1;
    private static final int YEAR_MIN = 1970;
    private static final int YEAR_MAX = 2100;
    private static final int MONTH_MIN = 1;
    private static final int MONTH_MAX = 12;
    private static final int DAY_MIN = 1;
    private static final int DAY_MAX = 31;
    private static final int HOUR_MIN = 0;
    private static final int HOUR_MAX = 23;
    private static final int MINUTE_MIN = 0;
    private static final int MINUTE_MAX = 59;

    /**
     * Returns the value of LocalDateTime entered from the console with minutes rounded to ten and seconds equal to 00
     *
     * @return LocalDateTime entered from console
     */
    public static LocalDateTime input(Scanner scanner) {
        StringBuilder dateBuilder = new StringBuilder();

        int year = enterDataIndicators(scanner, YEAR, YEAR_MIN, YEAR_MAX);
        int month = enterDataIndicators(scanner, MONTH, MONTH_MIN, MONTH_MAX);
        int day = enterDataIndicators(scanner, DAY, DAY_MIN, DAY_MAX);
        int hour = enterDataIndicators(scanner, HOUR, HOUR_MIN, HOUR_MAX);
        int minute = enterDataIndicators(scanner, MINUTE, MINUTE_MIN, MINUTE_MAX);

        dateBuilder.append(year);
        dateBuilder.append(DELIMITER_DATE);
        dateBuilder.append(String.format(TWO_DIGIT_FORMAT, month));
        dateBuilder.append(DELIMITER_DATE);
        dateBuilder.append(String.format(TWO_DIGIT_FORMAT, day));
        dateBuilder.append(DELIMITER_DATE_TIME);
        dateBuilder.append(String.format(TWO_DIGIT_FORMAT, hour));
        dateBuilder.append(DELIMITER_TIME);
        dateBuilder.append(minute);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return LocalDateTime.parse(dateBuilder, formatter);
    }

    /**
     * Checks for correct input
     *
     * @return true if input is not correct
     */
    private static boolean isNotCorrect(int number, int min, int max) {
        if (number < min || number > max) {
            System.out.printf(WARN_MESSAGE, min, max);
            return true;
        }
        return false;
    }

    /**
     * Validates the input of one of the date indicators and returns the correct value
     *
     * @return int value of the date indicators
     */
    private static int enterDataIndicators(Scanner scanner, String indicatorName, int indicatorMin, int indicatorMax) {
        int indicator = DEFAULT_VALUE;

        do {
            System.out.printf(MESSAGE_ENTER_DATE, indicatorName, indicatorMin, indicatorMax);
            try {
                indicator = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(ERROR_ENTER_MESSAGE);
            }
        } while (isNotCorrect(indicator, indicatorMin, indicatorMax));

        return indicator;
    }
}
