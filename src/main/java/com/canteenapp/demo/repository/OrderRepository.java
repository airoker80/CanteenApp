package com.canteenapp.demo.repository;

import com.canteenapp.demo.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> getOrdersByCreatedOnBetween(long start , long end);
}
