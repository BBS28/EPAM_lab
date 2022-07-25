package com.epam.lab.shchehlov.task_08.constant;

public class Constant {

    private Constant() {
    }

    //LongestSequenceApp
    public static final String ENTER_PATH = "Please enter path to file, or enter 0 to exit:";
    public static final String INPUT_ZERO = "0";
    public static final String FORMAT_RESULT_SEQUENCE = "Repeating sequence size - %d, first entry index - %d, second entry index - %d%n";
    public static final String FORMAT_CURRENT_RESULT = "Current value of the sequence length - %d%n";

    public static final String EXCEPTION_CONVERT = "Unable to convert file to list of bytes";

    public static final int DELAY = 100;

    //PrimeNumberSearcherApp
    public static final String SEPARATE = "Separate";
    public static final String COMMON = "Common";
    public static final String WITHOUT = "without";
    public static final String WITH = "with";

    public static final String FORMAT_RESULT_NUMBERS = "%s collection search %s executors process takes %s ms (%d ns) %s%n";

    public static final double COEFFICIENT_NS_TO_MS = 1000000.0;
    public static final int MIN_PRIME_NUMBER = 1;
    public static final int TIMEOUT_MINUTES = 1;

    public static final String ENTER_START_NUMBER = "Enter start number:";
    public static final String ENTER_END_NUMBER = "Enter end number:";
    public static final String ENTER_NUMBER_THREADS = "Enter number of threads:";
    public static final String WARNING_ENTER_NUMBER = "Please enter number!";
    public static final String WARNING_ENTER_IN_LIMIT = "Please enter a number between %d and %d!%n";

    //Common
    public static final String EXCEPTION_INTERRUPTED = "Thread was interrupted while occupied";

}
