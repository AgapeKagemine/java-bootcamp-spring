package com.bootcamp.weekly.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.weekly.model.Jasa;

public interface JasaRepository extends JpaRepository<Jasa, Long> {
    
}