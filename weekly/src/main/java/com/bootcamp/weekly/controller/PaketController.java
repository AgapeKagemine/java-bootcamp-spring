package com.bootcamp.weekly.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bootcamp.weekly.model.Paket;
import com.bootcamp.weekly.service.PaketService;

import jakarta.validation.Valid;

import java.util.List;

@RestController()
@RequestMapping("/api/paket")
public class PaketController {
    
    @Autowired
    private PaketService service;

    @GetMapping
    public ResponseEntity<List<Paket>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Paket> save(@Valid @RequestBody Paket paket) {
        return ResponseEntity.ok(service.save(paket));
    }
    
}
