package com.epam.lab.shchehlov.task_04.service.impl;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.repository.ProductRepository;
import com.epam.lab.shchehlov.task_04.service.ProductService;

import java.util.Hashtable;

/**
 * An implementation of the {@code ProductService} for working with a repository that stores a list of products
 *
 * @author B.Shchehlov
 */
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Hashtable<Long, PowerTool> getAll() {
        return productRepository.getAll();
    }

    @Override
    public PowerTool getOne(long id) {
        return productRepository.getOne(id);
    }

    @Override
    public PowerTool insert(PowerTool entity) {
        return productRepository.insert(entity);
    }

    @Override
    public PowerTool update(PowerTool entity) {
        return productRepository.update(entity);
    }

    @Override
    public PowerTool delete(long id) {
        return productRepository.delete(id);
    }
}
