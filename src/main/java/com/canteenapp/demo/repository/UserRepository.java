package com.canteenapp.demo.repository;

import com.canteenapp.demo.model.CanteenUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<CanteenUser, String> {
    @Override
    Optional<CanteenUser> findById(String s);

    CanteenUser findCanteenUserByUsername(String username);


}
