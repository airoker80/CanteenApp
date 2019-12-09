package com.canteenapp.demo.service.impl;

import com.canteenapp.demo.model.CanteenAuthority;
import com.canteenapp.demo.model.CanteenUser;
import com.canteenapp.demo.model.UserRoleName;
import com.canteenapp.demo.model.dao.CanteenUserDao;
import com.canteenapp.demo.model.dao.UserDao;
import com.canteenapp.demo.repository.UserRepository;
import com.canteenapp.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.canteenapp.demo.utils.ShortId.random62;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserDao user) {
        userRepository.save(
                new CanteenUser(
                        random62(7),
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singletonList(new CanteenAuthority(UserRoleName.ROLE_FUSE_EMPLOYEE)),
                        "",
                        false));
    }

    @Override
    public void save(CanteenUser user) {
        userRepository.save(user);
    }

    public List<CanteenUserDao> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(canteenUser -> new CanteenUserDao(canteenUser.getUsername()))
                .collect(Collectors.toList());
    }

    public void delete(String username) {
        CanteenUser canteenUser = userRepository.findCanteenUserByUsername(username);
        userRepository.delete(canteenUser);
    }
}
