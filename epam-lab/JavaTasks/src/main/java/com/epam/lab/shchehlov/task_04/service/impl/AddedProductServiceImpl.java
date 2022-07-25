package com.epam.lab.shchehlov.task_04.service.impl;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.repository.AddedProductsRepository;
import com.epam.lab.shchehlov.task_04.service.AddedProductsService;

import java.util.LinkedHashMap;

/**
 * An implementation of the {@code AddedProductsService} for working with a repository that stores a list of all
 * products added to basket in all sessions
 *
 * @author B.Shchehlov
 */
public class AddedProductServiceImpl implements AddedProductsService {
    private final AddedProductsRepository addedProductsRepository;

    public AddedProductServiceImpl(AddedProductsRepository addedProductsRepository) {
        this.addedProductsRepository = addedProductsRepository;
    }

    @Override
    public void insert(long id, PowerTool powerTool) {
        addedProductsRepository.insert(id, powerTool);
    }

    @Override
    public LinkedHashMap<Long, PowerTool> getAll() {
        return addedProductsRepository.getAll();
    }
}
