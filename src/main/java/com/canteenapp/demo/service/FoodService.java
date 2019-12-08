package com.canteenapp.demo.service;

import com.canteenapp.demo.model.Food;
import com.canteenapp.demo.model.dao.FoodDao;

import java.util.List;

public interface FoodService {
    void save(FoodDao food);

    FoodDao getFoodById(String id);

    List<FoodDao> getFoods();

    void update(String foodId, boolean isForToday);

    void update(FoodDao foodDao);

    List<FoodDao> getFoodsForToday();

    void delete(String foodId);
}
