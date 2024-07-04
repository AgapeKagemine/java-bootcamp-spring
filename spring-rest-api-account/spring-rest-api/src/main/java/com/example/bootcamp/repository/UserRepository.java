package com.example.bootcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bootcamp.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
