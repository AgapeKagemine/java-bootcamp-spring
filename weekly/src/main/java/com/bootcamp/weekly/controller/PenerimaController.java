package com.bootcamp.weekly.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bootcamp.weekly.model.Penerima;
import com.bootcamp.weekly.service.PenerimaService;

import jakarta.validation.Valid;

import java.util.List;

@RestController()
@RequestMapping("/api/penerima")
public class PenerimaController {
    
    @Autowired
    private PenerimaService service;

    @GetMapping
    public ResponseEntity<List<Penerima>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Penerima> save(@Valid @RequestBody Penerima penerima) {
        return ResponseEntity.ok(service.save(penerima));
    }
    
}
