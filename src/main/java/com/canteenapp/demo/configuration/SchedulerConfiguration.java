package com.canteenapp.demo.configuration;

import com.canteenapp.demo.model.CanteenUser;
import com.canteenapp.demo.model.Order;
import com.canteenapp.demo.repository.OrderRepository;
import com.canteenapp.demo.repository.UserRepository;
import com.canteenapp.demo.utils.ShortId;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * Run every day at 12pm (final order time) to check if all the users have ordered the lunch.
     * If not the default lunch will be ordered for them
     */
    @Scheduled(cron = "0 24 * * * *")
    public void scheduleOrders() {
        Stream<CanteenUser> users = userRepository.findCanteenUsersByHasOrderedFalse()
                .filter(canteenUser -> !canteenUser.getDefaultOrder().equals(""));
        List<Order> orders = users
                .map(canteenUser -> new Order(ShortId.random62(7), canteenUser.getDefaultOrder(), canteenUser.getUsername(), Order.Status.PENDING, System.currentTimeMillis()))
                .collect(Collectors.toList());
        List<CanteenUser> canteenUsers = users.peek(canteenUser -> canteenUser.setHasOrdered(true)).collect(Collectors.toList());
        userRepository.saveAll(canteenUsers);
        orderRepository.saveAll(orders);
    }
}

