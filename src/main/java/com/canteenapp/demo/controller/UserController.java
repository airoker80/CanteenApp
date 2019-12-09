package com.canteenapp.demo.controller;

import com.canteenapp.demo.model.CanteenUser;
import com.canteenapp.demo.model.dao.CanteenUserDao;
import com.canteenapp.demo.model.dao.UserDao;
import com.canteenapp.demo.security.auth.TokenBasedAuthentication;
import com.canteenapp.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<HttpStatus> create(@RequestBody UserDao userDao) {
        userDao.setPassword(passwordEncoder.encode(userDao.getPassword()));
        userService.save(userDao);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> update(@RequestParam String defaultFood, Principal principal) {
        CanteenUser canteenUser = (CanteenUser) ((TokenBasedAuthentication) principal).getPrincipal();
        canteenUser.setDefaultOrder(defaultFood);
        userService.save(canteenUser);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CanteenUserDao> getAll() {
        return userService.getUsers();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@RequestParam String username) {
        userService.delete(username);
        return ResponseEntity.ok("User deleted");
    }
}
