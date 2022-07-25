package com.epam.shchehlov.repository;

import com.epam.shchehlov.entity.Order;
import com.epam.shchehlov.entity.OrderedProduct;

public interface OrderRepository {

    Order addOrder(Order order);

    OrderedProduct addOrderedProduct(OrderedProduct orderedProduct, long orderId);
}
