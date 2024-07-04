package com.bootcamp.testjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.testjpa.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
