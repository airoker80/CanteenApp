package com.canteenapp.demo.service;

import com.canteenapp.demo.model.CanteenUser;
import com.canteenapp.demo.model.dao.CanteenUserDao;
import com.canteenapp.demo.model.dao.UserDao;

import java.util.List;

public interface UserService {
    void save(UserDao user);

    void save(CanteenUser user);

    List<CanteenUserDao> getUsers();

    CanteenUser getUserByUsername(String username);

    void delete(String username);
}
