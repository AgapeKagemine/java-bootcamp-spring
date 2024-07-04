package com.bootcamp.validation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.validation.44model.User;

public interface UserRepository extends JpaRepository<User, String> {
    
}
