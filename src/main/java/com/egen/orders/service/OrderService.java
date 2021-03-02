package com.egen.orders.service;

import com.egen.orders.domain.Order;

import java.util.List;

public interface OrderService {

    Order findOne(Long orderId);
    boolean checkIfOrderExists(Long orderId);
    Order finish(Long orderId);
    Order cancel(Long orderId);
    Order create(Order order);
    Order update(Order order);
    void saveBulkOrder(List<Order> orderList);
}
