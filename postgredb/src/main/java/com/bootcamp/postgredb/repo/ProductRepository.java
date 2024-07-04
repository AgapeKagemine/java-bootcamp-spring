package com.bootcamp.postgredb.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.postgredb.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPrice(double price, Pageable pageable);
}
