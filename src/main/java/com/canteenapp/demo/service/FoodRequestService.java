package com.canteenapp.demo.service;

import com.canteenapp.demo.model.Food;
import com.canteenapp.demo.model.FoodRequest;
import com.canteenapp.demo.model.dao.FoodDao;
import com.canteenapp.demo.model.dao.FoodRequestDao;

import java.util.List;

public interface FoodRequestService {
    void save(FoodRequestDao foodRequest);

    void update(FoodRequest foodRequest);

    List<FoodRequestDao> getRequests();

    void delete(String foodRequest);
}
