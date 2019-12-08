package com.canteenapp.demo.service;

import com.canteenapp.demo.model.CanteenUser;
import com.canteenapp.demo.model.dao.CanteenUserDao;
import com.canteenapp.demo.model.dao.UserDao;

import java.util.List;

public interface UserService {
    void save(UserDao user);

    List<CanteenUserDao> getUsers();

    void delete(String username);
}