package com.epam.shchehlov.service.impl;

import com.epam.shchehlov.database.transaction.TransactionManager;
import com.epam.shchehlov.database.transaction.impl.JdbcTransactionManager;
import com.epam.shchehlov.entity.Order;
import com.epam.shchehlov.entity.OrderedProduct;
import com.epam.shchehlov.repository.OrderRepository;
import com.epam.shchehlov.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TransactionManager transactionManager;

    public OrderServiceImpl(OrderRepository orderRepository, JdbcTransactionManager jdbcTransactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = jdbcTransactionManager;
    }

    @Override
    public Order addOrder(Order order) {
        Order updatedOrder =  transactionManager.doManipulationTransaction(() -> orderRepository.addOrder(order));
        for (OrderedProduct orderedProduct : updatedOrder.getProductList()) {
            transactionManager.doManipulationTransaction(() -> orderRepository.addOrderedProduct(orderedProduct, updatedOrder.getId()));
        }
        return updatedOrder;
    }
}
