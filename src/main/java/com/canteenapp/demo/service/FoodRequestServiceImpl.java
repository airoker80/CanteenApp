package com.canteenapp.demo.service;

import com.canteenapp.demo.model.FoodRequest;
import com.canteenapp.demo.model.dao.FoodRequestDao;
import com.canteenapp.demo.repository.FoodRequestRepository;
import com.canteenapp.demo.utils.ShortId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodRequestServiceImpl implements FoodRequestService {

    private final FoodRequestRepository repository;

    public FoodRequestServiceImpl(FoodRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(FoodRequestDao foodRequest) {
        repository.save(new FoodRequest(foodRequest.getUri(), foodRequest.getFoodName(), foodRequest.getRequestedBy(), System.currentTimeMillis()));
    }

    @Override
    public void update(FoodRequest foodRequest) {
        repository.save(foodRequest);
    }

    @Override
    public List<FoodRequestDao> getRequests() {
        return repository.findAll()
                .stream()
                .map(foodRequest -> new FoodRequestDao(foodRequest.getUri(),foodRequest.getFoodName(), foodRequest.getEmployName()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
