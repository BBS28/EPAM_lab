package com.epam.shchehlov.database.transaction;

import com.epam.shchehlov.database.TransactionOperation;

/**
 * The Transaction Manager defines the methods that allow an application server to manage transactions.
 *
 * @author B.Shchehlov.
 */
public interface TransactionManager {

    /**
     * executes a transaction requesting data from the database.
     */
    <T> T doInquiryTransaction(TransactionOperation<T> transactionOperation);

    /**
     * executes a transaction that changes data in the database.
     */
    <T> T doManipulationTransaction(TransactionOperation<T> transactionOperation);
}
