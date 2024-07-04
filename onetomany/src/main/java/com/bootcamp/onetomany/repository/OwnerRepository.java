package com.bootcamp.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.onetomany.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}