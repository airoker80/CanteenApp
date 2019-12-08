package com.canteenapp.demo.repository;

import com.canteenapp.demo.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FoodRepository extends MongoRepository<Food, String> {

    @Query("{ 'isForToday' : ?0 }")
    List<Food> findAllFoodsForToday(boolean isForToday);

}
