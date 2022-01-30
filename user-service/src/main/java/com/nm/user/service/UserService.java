package com.nm.user.service;

import com.nm.commonpojo.entities.User;

import org.springframework.stereotype.Component;

@Component
public interface UserService {

    public User save(User user);

    public User findByUserName(String userName);
    
}
