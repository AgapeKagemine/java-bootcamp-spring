package com.bootcamp.weekly.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.weekly.model.Lokasi;

public interface LokasiRepository extends JpaRepository<Lokasi, Long> {
    
}
