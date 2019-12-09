package com.canteenapp.demo.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodRequestDao {
    String uri;
    
    String foodName;

    String requestedBy;
}
