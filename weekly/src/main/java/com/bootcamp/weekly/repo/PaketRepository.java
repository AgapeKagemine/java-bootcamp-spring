package com.bootcamp.weekly.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.weekly.model.Paket;

public interface PaketRepository extends JpaRepository<Paket, Long> {
    
}
