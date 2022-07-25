package com.epam.lab.shchehlov.task_04.service;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;

import java.util.LinkedHashMap;

public interface AddedProductsService {
    void insert(long id, PowerTool powerTool);

    LinkedHashMap<Long, PowerTool> getAll();
}
