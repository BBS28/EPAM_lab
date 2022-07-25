package com.epam.lab.shchehlov.task_04.service;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.TreeMap;

/**
 * An interface of service for working with a repository that stores a list of orders made
 *
 * @author B.Shchehlov
 */
public interface OrderService {

    /**
     * Adds to the list of orders new order
     */
    void addOne(LocalDateTime date);

    /**
     * Returns the Hashtable of the products and quantities of the products in the order by the given date
     *
     * @return the Hashtable f the products and quantities of the products in the order by the given date
     */
    Hashtable<Long, Integer> getByDate(LocalDateTime date);

    /**
     * Returns the order's date that is closest to the given one
     */
    Hashtable<Long, Integer> getByClosestDate(LocalDateTime date);

    /**
     * Returns all added orders
     *
     * @return the TreeMap of all added orders
     */
    TreeMap<LocalDateTime, Hashtable<Long, Integer>> getAll();

    /**
     * Returns all added orders
     *
     * @return the TreeMap of all added orders
     */
    TreeMap<LocalDateTime, Hashtable<Long, Integer>> getOrdersForPeriod(LocalDateTime dateFrom, LocalDateTime dateTo);
}
