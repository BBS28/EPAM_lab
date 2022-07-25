package com.epam.shchehlov.database;

@FunctionalInterface
public interface TransactionOperation<T> {

    T execute();
}
