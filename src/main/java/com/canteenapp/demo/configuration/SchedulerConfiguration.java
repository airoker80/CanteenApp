package com.canteenapp.demo.configuration;

import com.canteenapp.demo.model.Order;
import com.canteenapp.demo.repository.OrderRepository;
import com.canteenapp.demo.repository.UserRepository;
import com.canteenapp.demo.utils.ShortId;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class SchedulerConfiguration {

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    public SchedulerConfiguration(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Run every day at 12pm to check if all the users have ordered the lunch.
     * If not order the lunch for them
     */
    @Scheduled(cron = "0 24 * * * *")
    public void scheduleOrders() {
        List<Order> orders = userRepository.findCanteenUsersByHasOrderedFalse()
                .map(canteenUser -> new Order(ShortId.random62(7), canteenUser.getDefaultOrder(), canteenUser.getUsername(), Order.Status.PENDING, System.currentTimeMillis()))
                .collect(Collectors.toList());

        orderRepository.saveAll(orders);
    }
}

