package com.bootcamp.weekly.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.weekly.model.Penerima;

public interface PenerimaRepository extends JpaRepository<Penerima, Long> {
    
}
