package com.epam.lab.shchehlov.task_04.repository.impl;

import com.epam.lab.shchehlov.task_04.repository.BasketRepository;
import org.apache.log4j.Logger;

import java.util.Hashtable;

/**
 * An implementation of the {@code BasketRepository} for working with a repository that stores a list of added products
 *
 * @author B.Shchehlov
 */
public class BasketRepositoryImpl implements BasketRepository {
    private static final Logger log = Logger.getLogger(BasketRepository.class.getName());
    private static final int DEFAULT_AMOUNT = 1;

    private Hashtable<Long, Integer> basket;

    public BasketRepositoryImpl() {
        this.basket = new Hashtable<>();
    }

    @Override
    public Hashtable<Long, Integer> getAll() {
        return basket;
    }

    @Override
    public void add(long id) {
        basket.put(id, DEFAULT_AMOUNT);
    }

    @Override
    public boolean updateAmount(long id, int amount) {
        if (!basket.containsKey(id)) {
            return false;
        }
        basket.put(id, amount);
        return true;
    }

    @Override
    public void deleteOne(long id) {
        basket.remove(id);
    }

    @Override
    public void deleteAll() {
        basket = new Hashtable<>();
    }

    @Override
    public boolean isEmpty() {
        return basket.isEmpty();
    }
}
