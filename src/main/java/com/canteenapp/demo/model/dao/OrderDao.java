package com.canteenapp.demo.model.dao;

import com.canteenapp.demo.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDao {
    String orderId;

    String foodName;

    String employeeName;

    Order.Status status;
}
