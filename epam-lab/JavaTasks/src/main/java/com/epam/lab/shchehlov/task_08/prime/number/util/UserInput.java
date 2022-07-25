package com.epam.lab.shchehlov.task_08.prime.number.util;

import com.epam.lab.shchehlov.task_08.constant.Constant;

import java.util.Scanner;

/**
 * Class for getting the data entered from the console.
 *
 * @author B.Shchehlov.
 */
public class UserInput {

    /**
     * Returns first search number entered from console.
     *
     * @param scanner input scanner.
     * @return first search number.
     */
    public static int startNumberInput(Scanner scanner) {
        return validateInt(scanner, Constant.MIN_PRIME_NUMBER, Integer.MAX_VALUE, Constant.ENTER_START_NUMBER);
    }

    /**
     * Returns last search number entered from console.
     *
     * @param scanner input scanner.
     * @return last search number.
     */
    public static int endNumberInput(Scanner scanner, int startNumber) {
        return validateInt(scanner, startNumber, Integer.MAX_VALUE, Constant.ENTER_END_NUMBER);
    }

    /**
     * Returns number of threads entered from console.
     *
     * @param scanner input scanner.
     * @return number of threads.
     */
    public static int threadNumberInput(Scanner scanner, int startNumber, int endNumber) {
        int maxThread = endNumber - startNumber;
        return validateInt(scanner, Constant.MIN_PRIME_NUMBER, maxThread, Constant.ENTER_NUMBER_THREADS);

    }

    /**
     * Returns a valid number input.
     *
     * @param scanner input scanner.
     * @param min     minimal value.
     * @param max     maximal value.
     * @param message input prompt.
     * @return valid number.
     */
    private static int validateInt(Scanner scanner, int min, int max, String message) {
        int input = 1;
        boolean isValidNumber = false;

        do {
            System.out.println(message);
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(Constant.WARNING_ENTER_NUMBER);
            }

            if (input >= min && input <= max) {
                isValidNumber = true;
            } else {
                System.out.printf(Constant.WARNING_ENTER_IN_LIMIT, min, max);
            }
        } while (!isValidNumber);

        return input;
    }
}
