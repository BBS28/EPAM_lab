package com.epam.lab.shchehlov.task_05.file.search.util;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.CHOICE_NO;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.CHOICE_YES;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.DATE_TIME_FORMAT;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.DAY;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.DAY_MAX;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.DAY_MIN;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.DEFAULT_VALUE;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.DELIMITER_DATE;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.DELIMITER_DATE_TIME;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.DELIMITER_TIME;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.ERROR_ENTER_MESSAGE;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.HOUR;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.HOUR_MAX;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.HOUR_MIN;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.MESSAGE_ENTER_DATE;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.MINUTE;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.MINUTE_MAX;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.MINUTE_MIN;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.MONTH;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.MONTH_MAX;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.MONTH_MIN;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.TWO_DIGIT_FORMAT;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.WARN_MESSAGE;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.YEAR;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.YEAR_MAX;
import static com.epam.lab.shchehlov.task_05.file.search.constant.Constant.YEAR_MIN;

/**
 * Class for getting the data entered from the console
 *
 * @author B.Shchehlov
 */
public class UserInput {

    /**
     * Returns 0 or 1 as analogous to the answer no or yes
     *
     * @return int 0 or 1
     */
    public static int inputChoice(Scanner scanner) {
        int result = DEFAULT_VALUE;
        while (true) {
            try {
                result = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a numerical value!");
            }
            if (result == CHOICE_NO || result == CHOICE_YES) {
                return result;
            }
            System.out.println("Enter 0 or 1:");
        }
    }

    /**
     * Returns a string value typed into the console.
     *
     * @return string value typed into the console.
     */
    public static String inputString(Scanner scanner) {
        return scanner.nextLine().trim();
    }

    /**
     * Returns long value typed into the console.
     *
     * @return long value typed into the console.
     */
    public static long inputLong(Scanner scanner) {
        long result = DEFAULT_VALUE;
        while (true) {
            try {
                result = Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a numerical value!");
            }
            if (result >= 0) {
                return result;
            }
            System.out.println("Enter a positive number: ");
        }
    }

    /**
     * Returns long value typed into the console.
     *
     * @return long value typed into the console.
     */
    public static File inputDirectory(Scanner scanner) {
        do {
            String path = scanner.nextLine().trim();
            File directory = new File(path);
            if (directory.isDirectory()) {
                return directory;
            }
            System.out.println("The entered value is not a directory. Specify the path to the folder:");
        } while (true);

    }

    /**
     * Returns the value of LocalDateTime entered from the console with minutes rounded to ten and seconds equal to 00
     *
     * @return LocalDateTime entered from console
     */
    public static LocalDateTime inputDate(Scanner scanner) {
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
        dateBuilder.append(String.format(TWO_DIGIT_FORMAT,minute));

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