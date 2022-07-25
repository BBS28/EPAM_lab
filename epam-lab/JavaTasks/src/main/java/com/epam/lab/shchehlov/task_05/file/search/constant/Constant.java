package com.epam.lab.shchehlov.task_05.file.search.constant;

public class Constant {
    public static final int CHOICE_YES = 1;
    public static final int CHOICE_NO = 0;
    public static final int DEFAULT_VALUE = -1 ;

    public static final String UTC = "UTC";
    public static final String TITLE = "<<FILE SEARCHER>>";
    public static final String ENTER_FOLDER_PATH = "Enter the path to the folder:";
    public static final String NO_FILES_FOUND = "No results were found according to these criteria";
    public static final String RESULTS = "Search results:";
    public static final String FORMAT_TOTAL_FOUND = "Total: %d files were found %n";
    public static final String BYE = "Bye!";

    public static final String WARNING_MAXIMUM_SIZE = "The maximum size must be greater than or equal to the minimum";
    public static final String WARNING_DATE_INPUT = "End date must be later than or equal start date";

    public static final String CONTINUE_SEARCHING = "Continue searching? (N/Y): 0/1";
    public static final String SEARCH_BY_NAME = "Search by file name (N/Y): 0/1";
    public static final String SEARCH_BY_EXTENSION = "Search by file extension (N/Y): 0/1";
    public static final String SEARCH_BY_SIZE = "Search by file size (N/Y): 0/1";
    public static final String SEARCH_BY_DATE = "Search by file by last modification date (N/Y): 0/1";
    public static final String ENTER_NAME = "Enter file name:";
    public static final String ENTER_EXTENSION = "Enter file extension:";
    public static final String ENTER_SIZE_MIN = "Enter minimal size:";
    public static final String ENTER_SIZE_MAX = "Enter maximal size:";
    public static final String ENTER_DATE_MIN = "Enter start date:";
    public static final String ENTER_DATE_MAX = "Enter end date:";

    public static final String MESSAGE_ENTER_DATE = "Enter %s (%d - %d):%n";
    public static final String ERROR_ENTER_MESSAGE = "Please enter a numerical value!";
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String TWO_DIGIT_FORMAT = "%02d";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String WARN_MESSAGE = "Please enter a number within %d - %d%n";

    public static final char POINT = '.';
    public static final char DELIMITER_DATE = '-';
    public static final char DELIMITER_TIME = ':';
    public static final char DELIMITER_DATE_TIME = ' ';
    public static final int YEAR_MIN = 1970;
    public static final int YEAR_MAX = 2100;
    public static final int MONTH_MIN = 1;
    public static final int MONTH_MAX = 12;
    public static final int DAY_MIN = 1;
    public static final int DAY_MAX = 31;
    public static final int HOUR_MIN = 0;
    public static final int HOUR_MAX = 23;
    public static final int MINUTE_MIN = 0;
    public static final int MINUTE_MAX = 59;
}
