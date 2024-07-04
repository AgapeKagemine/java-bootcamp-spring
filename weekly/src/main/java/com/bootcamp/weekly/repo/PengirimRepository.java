package com.bootcamp.weekly.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.weekly.model.Pengirim;

public interface PengirimRepository extends JpaRepository<Pengirim, Long> {

}
