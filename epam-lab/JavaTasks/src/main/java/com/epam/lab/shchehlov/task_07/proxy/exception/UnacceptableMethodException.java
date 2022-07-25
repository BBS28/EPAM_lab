package com.epam.lab.shchehlov.task_07.proxy.exception;

/**
 * Exception that is thrown when trying to access a method to which access is denied
 *
 * @author B. Shchehlov
 */
public class UnacceptableMethodException extends RuntimeException{
    public UnacceptableMethodException(String message) {
        super(message);
    }
}
