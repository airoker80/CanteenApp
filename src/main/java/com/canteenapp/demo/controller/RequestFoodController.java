package com.canteenapp.demo.controller;

import com.canteenapp.demo.model.dao.FoodRequestDao;
import com.canteenapp.demo.service.FoodRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/requests")
public class RequestFoodController {

    @Autowired
    FoodRequestService requestService;

    @RequestMapping(method = RequestMethod.POST)
    public void addRequest(@RequestBody FoodRequestDao request){
        requestService.save(request);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<FoodRequestDao> getRequest(){
        return requestService.getRequests();
    }
}
