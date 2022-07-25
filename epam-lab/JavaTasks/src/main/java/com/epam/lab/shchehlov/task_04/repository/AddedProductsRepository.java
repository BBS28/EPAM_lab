package com.epam.lab.shchehlov.task_04.repository;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;

import java.util.LinkedHashMap;

/**
 * An interface for working with a repository that stores a list of all products added to basket in all sessions
 *
 * @author B.Shchehlov
 */
public interface AddedProductsRepository {

    /**
     * Adds PowerTool and {@code id} of it to the list of all products added to basket in all sessions
     */
    void insert(long id, PowerTool powerTool);

    /**
     * Returns the LinkedHashMap of the products added to basket in all sessions
     *
     * @return LinkedHashMap of the products added to basket in all sessions
     */
    LinkedHashMap<Long, PowerTool> getAll();

}
