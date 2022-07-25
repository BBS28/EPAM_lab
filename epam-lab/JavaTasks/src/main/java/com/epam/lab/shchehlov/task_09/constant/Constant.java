package com.epam.lab.shchehlov.task_09.constant;

public class Constant {

    private Constant() {
    }

    public static final String PRODUCT_DATA_FILE = "src\\main\\resources\\task_06\\products.txt";

    public static final String INPUT_COMMAND = "Input command:";
    public static final String CONTINUE_COMMAND = "Press 0 to stop or any else input to continue";
    public static final String NO_COMMAND = "product with id %d doesn't exist!";
    public static final String INVALID_COMMAND = "There is no such server command";

    public static final String FORMAT_THREAD_STARTED = "%s Started!";
    public static final String FORMAT_OUTPUT_GET_ITEM = "%s | %d";
    public static final String FORMAT_JSON_GET_COUNT = "{count:%s}";
    public static final String FORMAT_JSON_GET_ITEM = "{name:%s, price:%d}";
    public static final String FORMAT_NO_PRODUCT = "product with id %d doesn't exist!";

    public static final String FORMAT_REQUEST_RECEIVED = "request received - %s";
    public static final String FORMAT_RESPONSE_SENT = "request sent - %s";

    public static final String REGEX_TCP_COMMAND = "get\\sitem=\\d+|get\\scount";
    public static final String REGEX_HTTP_COMMAND = "GET\\s/shop/(item\\?get_info=\\d+|count)\\sHTTP/1.1";
    public static final String REGEX_HTTP_DELIMITER = "\\?";
    public static final String REGEX_EQUALS = "=";

    public static final String COMMAND_NO_COMMAND = "no server command";
    public static final String COMMAND_GET_COUNT = "get count";
    public static final String COMMAND_GET_ITEM = "get item";
    public static final String COMMAND_COUNT = "count";
    public static final String COMMAND_ITEM = "item";
    public static final String COMMAND_STOP_CLIENT = "0";

    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int TCP_PORT = 3000;

    public static final String EXCEPTION_INTERRUPTED = "Thread was interrupted while occupied";
    public static final String EXCEPTION_READING_DATA = "Reading data error";
    public static final String FILE_NOT_FOUND = " Data file not found or damaged!";

    public static final String LINE_HTTP = "HTTP/1.1 ";
    public static final String LINE_CONTENT_TYPE = "Content-Type: text/html; charset=UTF-8";
    public static final String LINE_CONNECTION = "Connection: close";
}
