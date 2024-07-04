package com.bootcamp.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.onetomany.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>{
    
}
