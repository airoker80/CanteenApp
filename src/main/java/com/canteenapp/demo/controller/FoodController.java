package com.canteenapp.demo.controller;

import com.canteenapp.demo.model.dao.FoodDao;
import com.canteenapp.demo.service.FoodService;
import com.mongodb.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @RequestMapping(value = "/{isForToday}", method = RequestMethod.GET)
    public List<FoodDao> getFoods(@Nullable @PathVariable Boolean isForToday) {
        if (isForToday != null && isForToday) {
            return foodService.getFoodsForToday();
        }
        return foodService.getFoods();
    }

    @RequestMapping(value = "/{isForToday}",method = RequestMethod.PUT)
    public void updateFood(@RequestBody FoodDao food, @PathVariable Boolean isForToday) {
        if (isForToday != null){
            foodService.update(food.getFoodId(), isForToday);
        }
        foodService.update(food);
        log.info("food {} updated", food);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteFood(@RequestBody FoodDao foodDao) {
        foodService.delete(foodDao.getFoodId());
        log.info("food {} deleted", foodDao);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addFood(@RequestBody FoodDao foodDao) {
        foodService.save(foodDao);
    }
}
