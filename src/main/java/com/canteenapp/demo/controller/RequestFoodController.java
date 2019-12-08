package com.canteenapp.demo.controller;

import com.canteenapp.demo.model.CanteenUser;
import com.canteenapp.demo.model.FoodRequest;
import com.canteenapp.demo.model.dao.FoodRequestDao;
import com.canteenapp.demo.security.auth.TokenBasedAuthentication;
import com.canteenapp.demo.service.FoodRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/requests")
public class RequestFoodController {

    private final FoodRequestService requestService;

    public RequestFoodController(FoodRequestService requestService) {
        this.requestService = requestService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addRequest(@RequestBody FoodRequestDao request, Principal principal){
        CanteenUser canteenUser = (CanteenUser) ((TokenBasedAuthentication)principal).getPrincipal();
        FoodRequestDao foodRequest = new FoodRequestDao(request.getFoodName(), canteenUser.getUsername());
        requestService.save(foodRequest);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<FoodRequestDao> getRequest(){
        return requestService.getRequests();
    }
}
