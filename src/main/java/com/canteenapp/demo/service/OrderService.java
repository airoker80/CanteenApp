package com.canteenapp.demo.service;

import com.canteenapp.demo.model.Order;
import com.canteenapp.demo.model.dao.OrderDao;

import java.security.Principal;
import java.util.List;

public interface OrderService {
    void save(String foodId, Principal principal);

    int save(List<String> food, Principal principal);

    List<OrderDao> getOrders();

    List<OrderDao> getOrdersByCreatedDate(long start, long end);

    void delete(String order);

    void update(String orderId , Order.Status status);
}
