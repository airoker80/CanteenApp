package com.canteenapp.demo.controller;

import com.canteenapp.demo.model.CanteenUser;
import com.canteenapp.demo.model.dao.OrderDao;
import com.canteenapp.demo.security.auth.TokenBasedAuthentication;
import com.canteenapp.demo.service.OrderService;
import com.mongodb.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public String addOrder(@RequestBody String foodId, Principal principal) {
        orderService.save(foodId, principal);
        return "created";
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<OrderDao> getOrders(Principal principal, @Nullable @RequestParam Long start, @RequestParam @Nullable Long end) {
        if ((start != null) && (end != null)) {
            return  orderService.getOrdersByCreatedDate(start, end);
        }
        return orderService.getOrders();
    }

}
