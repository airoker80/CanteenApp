package com.canteenapp.demo.service;

import com.canteenapp.demo.model.CanteenUser;
import com.canteenapp.demo.model.Order;
import com.canteenapp.demo.model.dao.FoodDao;
import com.canteenapp.demo.model.dao.OrderDao;
import com.canteenapp.demo.repository.OrderRepository;
import com.canteenapp.demo.security.auth.TokenBasedAuthentication;
import com.canteenapp.demo.utils.ShortId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {

    private final FoodService foodService;

    private final UserService userService;

    private final OrderRepository orderRepository;

    public OrderServiceImpl(FoodService foodService, UserService userService, OrderRepository orderRepository) {
        this.foodService = foodService;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public int save(String foodId, Principal principal) {
        FoodDao foodDao = foodService.getFoodById(foodId);
        CanteenUser canteenUser = (CanteenUser) ((TokenBasedAuthentication)principal).getPrincipal();
        orderRepository.save(new Order(ShortId.random62(7), foodDao.getFoodName(), canteenUser.getUsername(), Order.Status.PENDING, System.currentTimeMillis()));
        canteenUser.setHasOrdered(true);
        userService.save(canteenUser);
        return foodDao.getFoodPrice();
    }

    @Override
    public int save(List<String> foodIds, Principal principal) {
        CanteenUser canteenUser = (CanteenUser) ((TokenBasedAuthentication)principal).getPrincipal();
        Stream<FoodDao> foodDaoStream = foodIds
                .stream()
                .map(foodService::getFoodById);
        List<Order> orders = foodDaoStream
                .map(foodDao -> new Order(ShortId.random62(7), foodDao.getFoodName(), canteenUser.getUsername(), Order.Status.PENDING, System.currentTimeMillis()))
                .collect(Collectors.toList());
        orderRepository.saveAll(orders);
        canteenUser.setHasOrdered(true);
        userService.save(canteenUser);
        return foodDaoStream.mapToInt(FoodDao::getFoodPrice).sum();
    }

    @Override
    public List<OrderDao> getOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(order -> new OrderDao(order.getOrderId(),order.getFoodName(), order.getEmployeeName(), order.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDao> getOrdersByCreatedDate(long start, long end) {
        return orderRepository.getOrdersByCreatedOnBetween(start, end).stream()
                .map(order -> new OrderDao(order.getOrderId(),order.getFoodName(), order.getEmployeeName(), order.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void update(String orderId , Order.Status status) {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(status);
        orderRepository.save(order);
    }
}
