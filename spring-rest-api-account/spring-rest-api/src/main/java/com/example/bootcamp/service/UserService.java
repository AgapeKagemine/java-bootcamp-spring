package com.example.bootcamp.service;

import java.util.List;

import com.example.bootcamp.model.User;

public interface UserService {
    List<User> listAll();

    User get(String userId);

    User save(User user);

    void delete(String userId);
}