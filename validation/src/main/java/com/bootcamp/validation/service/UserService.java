package com.bootcamp.validation.service;

import java.util.List;

import com.bootcamp.validation.model.User;

public interface UserService {
    List<User> listAll();

    User get(String userId);

    User save(User user);

    void delete(String userId);
}
