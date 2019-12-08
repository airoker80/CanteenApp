package com.canteenapp.demo.controller;

import com.canteenapp.demo.model.dao.FoodDao;
import com.canteenapp.demo.service.FoodService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<FoodDao> getFoods() {
        return foodService.getFoods();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addFood(@RequestBody FoodDao foodDao) {
        foodService.save(foodDao);
    }
}
