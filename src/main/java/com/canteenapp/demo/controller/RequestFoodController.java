package com.canteenapp.demo.controller;

import com.canteenapp.demo.model.CanteenUser;
import com.canteenapp.demo.model.FoodRequest;
import com.canteenapp.demo.model.dao.FoodRequestDao;
import com.canteenapp.demo.security.auth.TokenBasedAuthentication;
import com.canteenapp.demo.service.FoodRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void add(@RequestBody FoodRequestDao request, Principal principal) {
        CanteenUser canteenUser = (CanteenUser) ((TokenBasedAuthentication) principal).getPrincipal();
        FoodRequestDao foodRequest = new FoodRequestDao(request.getFoodName(), canteenUser.getUsername());
        requestService.save(foodRequest);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<FoodRequestDao> get() {
        return requestService.getRequests();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody FoodRequest foodRequest) {
        requestService.update(foodRequest);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam String foodId) {
        requestService.delete(foodId);
        return ResponseEntity.ok("success");
    }
}
