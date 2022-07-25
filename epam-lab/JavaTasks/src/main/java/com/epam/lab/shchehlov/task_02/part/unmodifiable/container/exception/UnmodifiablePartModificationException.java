package com.epam.lab.shchehlov.task_02.part.unmodifiable.container.exception;

public class UnmodifiablePartModificationException extends IllegalArgumentException {
    public UnmodifiablePartModificationException(String message) {
        super(message);
    }
}
