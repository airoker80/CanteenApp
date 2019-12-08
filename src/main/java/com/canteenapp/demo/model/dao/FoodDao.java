package com.canteenapp.demo.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodDao {
    public String foodName;

    public int foodPrice;

    public boolean isForToday;
}
