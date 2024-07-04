package com.bootcamp.weekly.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bootcamp.weekly.model.Pengirim;
import com.bootcamp.weekly.service.PengirimService;

import jakarta.validation.Valid;

import java.util.List;

@RestController()
@RequestMapping("/api/pengirim")
public class PengirimController {
    
    @Autowired
    private PengirimService service;

    @GetMapping
    public ResponseEntity<List<Pengirim>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Pengirim> save(@Valid @RequestBody Pengirim pengirim) {
        return ResponseEntity.ok(service.save(pengirim));
    }
    
}
