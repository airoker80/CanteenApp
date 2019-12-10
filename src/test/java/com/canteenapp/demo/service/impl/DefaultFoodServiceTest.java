package com.canteenapp.demo.service.impl;

import com.canteenapp.demo.model.CanteenUser;
import com.canteenapp.demo.model.Food;
import com.canteenapp.demo.model.dao.FoodDao;
import com.canteenapp.demo.model.dao.OrderDao;
import com.canteenapp.demo.model.dao.UserDao;
import com.canteenapp.demo.security.auth.TokenBasedAuthentication;
import com.canteenapp.demo.service.FoodService;
import com.canteenapp.demo.service.OrderService;
import com.canteenapp.demo.service.UserService;
import com.canteenapp.demo.utils.ShortId;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DefaultFoodServiceTest {
    @Autowired
    private FoodService foodService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    private TokenBasedAuthentication principal = Mockito.mock(TokenBasedAuthentication.class);

    @BeforeAll
    public void before() {
        mongoTemplate.getDb().drop();
        UserDao test_user = new UserDao("test_user", "12345");
        userService.save(test_user);
        CanteenUser canteenUser = userService.getUserByUsername(test_user.getUsername());
        FoodDao momo = new FoodDao(ShortId.random62(), "mo mo", 100);
        foodService.save(momo);
        FoodDao curry = new FoodDao(ShortId.random62(), "curry", 200);
        foodService.save(curry);
        when(principal.getPrincipal()).thenReturn(canteenUser);

        List<String> foods = foodService
                .getFoods()
                .stream()
                .map(FoodDao::getFoodId).collect(Collectors.toList());
        orderService.save(foods, principal);
    }

    @Test
    public void testFoodService() {
        List<OrderDao> orders = orderService.getOrders();
        assertEquals(2, orders.size());
        assertTrue(userService.getUserByUsername("test_user").isHasOrdered());
    }
}
