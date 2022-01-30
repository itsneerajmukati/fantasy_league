package com.nm.user.repository;

import com.nm.commonpojo.entities.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUserName(String userName);
    
}
