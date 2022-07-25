package com.epam.shchehlov.repository.impl;

import com.epam.shchehlov.entity.Order;
import com.epam.shchehlov.entity.OrderedProduct;
import com.epam.shchehlov.repository.OrderRepository;
import com.epam.shchehlov.repository.template.JdbcTemplate;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OrderRepositoryImpl implements OrderRepository {

    private static final  String INSERT_ORDER = "INSERT INTO ptshop.orders (order_status, state_detail, date_time, user_id, payment_details, delivery_payment) VALUES (?,?,?,?,?,?)";
    private static final  String INSERT_ORDERED_PRODUCT = "INSERT INTO ptshop.ordered_products (product_id, order_id, product_price, number_of_products) VALUES (?,?,?,?)";

    private static final Logger logger = Logger.getLogger(OrderRepositoryImpl.class);
    private final JdbcTemplate<Order> orderJdbcTemplate;
    private final JdbcTemplate<OrderedProduct> orderedProductJdbcTemplate;

    public OrderRepositoryImpl(JdbcTemplate<Order> orderJdbcTemplate, JdbcTemplate<OrderedProduct> orderedProductJdbcTemplate) {
        this.orderJdbcTemplate = orderJdbcTemplate;
        this.orderedProductJdbcTemplate = orderedProductJdbcTemplate;
    }

    @Override
    public Order addOrder(Order order) {
        LocalDateTime creatingDateTime = LocalDateTime.now();
        order.setDateTime(creatingDateTime);
        Object[] values = {order.getOrderStatus().toString(),
                order.getStateDetail(),
                Timestamp.valueOf(order.getDateTime()),
                order.getUserId(),
                order.getPaymentDetails(),
                order.getDeliveryPayment().toString()};
        for (Object value : values) {
            logger.debug("order values ==> " + value.toString());
        }

        long orderId = orderJdbcTemplate.executeUpdate(INSERT_ORDER, values);
        order.setId(orderId);
        return order;
    }

    @Override
    public OrderedProduct addOrderedProduct(OrderedProduct orderedProduct, long orderId) {
        Object[] values = {orderedProduct.getProductId(),
                orderId,
                orderedProduct.getProductPrice(),
                orderedProduct.getNumberOfProducts()};

        for (Object value : values) {
            logger.debug("ordered products values ==> " + value.toString());
        }
        orderedProductJdbcTemplate.executeUpdate(INSERT_ORDERED_PRODUCT, values);
        return orderedProduct;
    }
}
