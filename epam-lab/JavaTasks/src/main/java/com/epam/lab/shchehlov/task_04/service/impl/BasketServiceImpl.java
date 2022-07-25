package com.epam.lab.shchehlov.task_04.service.impl;

import com.epam.lab.shchehlov.task_04.repository.BasketRepository;
import com.epam.lab.shchehlov.task_04.service.BasketService;

import java.util.Hashtable;

/**
 * An implementation of the {@code BasketService} for working with a repository that stores a list of added products
 *
 * @author B.Shchehlov
 */
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public Hashtable<Long, Integer> getAll() {
        return basketRepository.getAll();
    }

    @Override
    public void add(long id) {
        basketRepository.add(id);
    }

    @Override
    public boolean updateAmount(long id, int amount) {
        return basketRepository.updateAmount(id, amount);
    }

    @Override
    public void deleteAll() {
        basketRepository.deleteAll();
    }

    @Override
    public boolean isEmpty() {
        return basketRepository.isEmpty();
    }
}
