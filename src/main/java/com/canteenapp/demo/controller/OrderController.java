package com.canteenapp.demo.controller;

import com.canteenapp.demo.model.Order;
import com.canteenapp.demo.model.dao.FoodDao;
import com.canteenapp.demo.model.dao.OrderDao;
import com.canteenapp.demo.service.OrderService;
import com.mongodb.lang.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Integer> addOrder(@RequestBody FoodDao foodDao, Principal principal) {
        int price = orderService.save(foodDao.foodId, principal);
        Map<String, Integer> map = new HashMap<>();
        map.put("totalPrice", price);
        return map;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<OrderDao> getOrders(@Nullable @RequestParam Long start, @RequestParam @Nullable Long end) {
        if ((start != null) && (end != null)) {
            return  orderService.getOrdersByCreatedDate(start, end);
        }
        return orderService.getOrders();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> updateOrder(@RequestBody Order order ){
        orderService.update(order.getOrderId(), order.getStatus());
        return ResponseEntity.ok("success");
    }

}
