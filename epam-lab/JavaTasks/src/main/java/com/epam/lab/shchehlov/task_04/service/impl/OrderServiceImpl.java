package com.epam.lab.shchehlov.task_04.service.impl;

import com.epam.lab.shchehlov.task_04.repository.BasketRepository;
import com.epam.lab.shchehlov.task_04.repository.OrderRepository;
import com.epam.lab.shchehlov.task_04.service.OrderService;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.TreeMap;

/**
 * An implementation of the {@code OrderService} for working with a repository that stores a list of orders made
 *
 * @author B.Shchehlov
 */
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;

    public OrderServiceImpl(OrderRepository orderRepository, BasketRepository basketRepository) {
        this.orderRepository = orderRepository;
        this.basketRepository = basketRepository;
    }

    @Override
    public void addOne(LocalDateTime date) {
        orderRepository.addOne(date, basketRepository.getAll());
        basketRepository.deleteAll();
    }

    @Override
    public Hashtable<Long, Integer> getByDate(LocalDateTime date) {
        return orderRepository.getByDate(date);
    }

    @Override
    public Hashtable<Long, Integer> getByClosestDate(LocalDateTime date) {
        return orderRepository.getByClosestDate(date);
    }

    @Override
    public TreeMap<LocalDateTime, Hashtable<Long, Integer>> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public TreeMap<LocalDateTime, Hashtable<Long, Integer>> getOrdersForPeriod(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return orderRepository.getOrdersForPeriod(dateFrom, dateTo);
    }
}
