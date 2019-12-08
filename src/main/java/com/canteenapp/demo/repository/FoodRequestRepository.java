package com.canteenapp.demo.repository;

import com.canteenapp.demo.model.FoodRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRequestRepository extends MongoRepository<FoodRequest, String> {
}
