package com.epam.lab.shchehlov.task_04.service;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;

import java.util.Hashtable;

/**
 * An interface of service for working with a repository that stores a list of products
 *
 * @author B.Shchehlov
 */
public interface ProductService {

    /**
     * Returns the Hashtable of all added products
     *
     * @return the Hashtable of all added products
     */
    Hashtable<Long, PowerTool> getAll();

    /**
     * Returns the PowerTool by given {@code id}
     *
     * @return the PowerTool by given {@code id}
     */
    PowerTool getOne(long id);

    /**
     * Adds to the list of products new PowerTool
     *
     * @return the PowerTool with updated {@code id}
     */
    PowerTool insert(PowerTool entity);

    /**
     * Makes changes to the PowerTool from the list of products
     *
     * @return updated PowerTool
     */
    PowerTool update(PowerTool entity);

    /**
     * Deletes the PowerTool from the list of products by {@code id}
     *
     * @return deleted PowerTool
     */
    PowerTool delete(long id);
}
