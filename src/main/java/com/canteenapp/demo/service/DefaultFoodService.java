package com.canteenapp.demo.service;

import com.canteenapp.demo.model.Food;
import com.canteenapp.demo.model.dao.FoodDao;
import com.canteenapp.demo.repository.FoodRepository;
import com.canteenapp.demo.utils.ShortId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFoodService implements FoodService {

    private final FoodRepository foodRepository;

    public DefaultFoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public void save(FoodDao food) {
        foodRepository.save(new Food(ShortId.random62(7), food.getFoodName(), food.getFoodPrice(), false));
    }

    @Override
    public FoodDao getFoodById(String id) {
        Food food = foodRepository.findById(id).get();
        return new FoodDao(food.getFoodId(),food.getFoodName(), food.getFoodPrice());
    }

    public void update(String foodId, boolean isForToday) {
        Food food = foodRepository.findById(foodId).get();
        food.setForToday(isForToday);
        foodRepository.save(food);
    }

    @Override
    public List<FoodDao> getFoods() {
        return convertToFoodDao(foodRepository.findAll());
    }
    @Override
    public List<FoodDao> getFoodsForToday() {
        return convertToFoodDao(foodRepository.findAllFoodsForToday(true));
    }

    @Override
    public void delete(String id) {
        foodRepository.deleteById(id);
    }

    private List<FoodDao> convertToFoodDao(List<Food>foods) {
        return  foods
                .stream()
                .map(foodDocument -> new FoodDao(foodDocument.getFoodId(),foodDocument.getFoodName(), foodDocument.getFoodPrice()))
                .collect(Collectors.toList());
    }

}
