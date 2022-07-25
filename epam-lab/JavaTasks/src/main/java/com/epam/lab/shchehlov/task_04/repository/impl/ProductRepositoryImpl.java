package com.epam.lab.shchehlov.task_04.repository.impl;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.repository.ProductRepository;

import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * An implementation of the {@code ProductRepository} for working with a repository that stores a list of products
 *
 * @author B.Shchehlov
 */
public class ProductRepositoryImpl implements ProductRepository {
    private static final String MESSAGE_NOT_EXIST = "Product with ID = %d does not exist";

    private final Hashtable<Long, PowerTool> productList;

    public ProductRepositoryImpl() {
        this.productList = new Hashtable<>();
    }

    public ProductRepositoryImpl(PowerTool[] powerTools) {
        productList = new Hashtable<>();
        for (PowerTool tool : powerTools) {
            insert(tool);
        }
    }

    @Override
    public Hashtable<Long, PowerTool> getAll() {
        return this.productList;
    }

    @Override
    public PowerTool getOne(long id) {
        return productList.get(id);
    }

    @Override
    public PowerTool insert(PowerTool powerTool) {
        long id = keyDeterminant(powerTool.getId());
        powerTool.setId(id);
        return productList.put(id, powerTool);
    }

    @Override
    public PowerTool update(PowerTool powerTool) {
        long id = powerTool.getId();
        validateId(id);
        productList.put(id, powerTool);
        return powerTool;
    }

    @Override
    public PowerTool delete(long id) {
        validateId(id);
        return productList.remove(id);
    }

    /**
     * Checks for presence {@code id} in the list of products
     *
     * @throws NoSuchElementException if there is no such product with {@code id} in this list
     */
    private void validateId(long id) {
        if (!productList.containsKey(id)) {
            throw new NoSuchElementException(String.format(MESSAGE_NOT_EXIST, id));
        }
    }

    /**
     * Checks id of product for presence in the list and, if present, returns a new one
     *
     * @return long id
     */
    private long keyDeterminant(long id) {
        long resultId = id;
        if (productList.containsKey(resultId)) {
            resultId = keyDeterminant(++resultId);
        }
        return resultId;
    }
}
