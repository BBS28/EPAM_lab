package com.epam.lab.shchehlov.task_04.repository.impl;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.repository.AddedProductsRepository;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An implementation of the {@code AddedProductsRepository} for working with a repository that stores a list of all
 * products added to basket in all sessions
 *
 * @author B.Shchehlov
 */
public class AddedProductsRepositoryImpl implements AddedProductsRepository {
    private static final int CAPACITY = 5;

    private final LinkedHashMap<Long, PowerTool> addedProductList;

    public AddedProductsRepositoryImpl() {
        this.addedProductList = new LinkedHashMap<Long, PowerTool>() {
            @Override
            protected boolean removeEldestEntry(final Map.Entry<Long, PowerTool> eldest) {
                return size() > CAPACITY;
            }
        };
    }

    @Override
    public void insert(long id, PowerTool powerTool) {
        addedProductList.put(id, powerTool);
    }

    @Override
    public LinkedHashMap<Long, PowerTool> getAll() {
        return addedProductList;
    }
}
