package com.epam.shchehlov.entity;

import com.epam.shchehlov.entity.attribute.DeliveryPayment;
import com.epam.shchehlov.entity.attribute.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Order extends Entity {

    private OrderStatus orderStatus;
    private String stateDetail;
    private String paymentDetails;
    private DeliveryPayment deliveryPayment;
    private LocalDateTime dateTime;
    private long userId;
    private List<OrderedProduct> productList;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStateDetail() {
        return stateDetail;
    }

    public void setStateDetail(String stateDetail) {
        this.stateDetail = stateDetail;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public DeliveryPayment getDeliveryPayment() {
        return deliveryPayment;
    }

    public void setDeliveryPayment(DeliveryPayment deliveryPayment) {
        this.deliveryPayment = deliveryPayment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

        public List<OrderedProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderedProduct> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", stateDetail='" + stateDetail + '\'' +
                ", paymentDetails='" + paymentDetails + '\'' +
                ", deliveryPayment=" + deliveryPayment +
                ", dateTime=" + dateTime +
                ", userId=" + userId +
                ", productList=" + productList +
                '}';
    }
}
