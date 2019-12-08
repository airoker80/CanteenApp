package com.canteenapp.demo.configuration;


import com.canteenapp.demo.model.CanteenAuthority;
import com.canteenapp.demo.model.CanteenUser;
import com.canteenapp.demo.model.UserRoleName;
import com.canteenapp.demo.model.dao.FoodDao;
import com.canteenapp.demo.repository.UserRepository;
import com.canteenapp.demo.service.FoodService;
import com.canteenapp.demo.utils.ShortId;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AppBootstrap
        implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userService;
    private final FoodService foodService;
    private final PasswordEncoder passwordEncoder;

    public AppBootstrap(UserRepository userService, FoodService foodService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.foodService = foodService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!(userService.findAll().size() > 0)){
            userService.save(
                    new CanteenUser(
                            ShortId.random62(),
                            "Admin",
                            passwordEncoder.encode("p@ssW0rD"),
                            Collections.singletonList(new CanteenAuthority(UserRoleName.ROLE_ADMIN))));

//        foodService.save(new FoodDao("Mo Mo ", 100));
//        foodService.save(new FoodDao("Noodles ", 100));
//        foodService.save(new FoodDao("Fried Rice ", 100));
//        foodService.save(new FoodDao("Curry ", 100));
        }
    }
}
