package com.epam.lab.shchehlov.task_04.service;

import java.util.Hashtable;

/**
 * An interface of service for working with a repository that stores a list of products added to basket
 *
 * @author B.Shchehlov
 */
public interface BasketService {

    /**
     * Returns the Hashtable of the products and quantities of the products in the basket
     *
     * @return the Hashtable f the products and quantities of the products in the basket
     */
    Hashtable<Long, Integer> getAll();

    /**
     * Adds {@code id} of the product to the list of products in basket
     */
    void add(long id);

    /**
     * Changes the amount of product {@code id} in basket
     *
     * @return true if the key exists and the quantity is changed
     */
    boolean updateAmount(long id, int amount);

    /**
     * Deletes product from basket by {@code id}
     */
    void deleteAll();

    /**
     * Returns true if there is any added product to basket
     *
     * @return true if there is any added product to basket
     */
    boolean isEmpty();
}
