package com.bootcamp.testjpa.service;

import java.util.List;

import com.bootcamp.testjpa.model.User;

public interface UserService {
    List<User> listAll();

    User get(String userId);

    User save(User user);

    void delete(String userId);
}
