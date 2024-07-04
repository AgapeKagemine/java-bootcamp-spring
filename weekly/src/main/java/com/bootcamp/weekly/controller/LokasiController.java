package com.bootcamp.weekly.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bootcamp.weekly.model.Lokasi;
import com.bootcamp.weekly.service.LokasiService;

import jakarta.validation.Valid;

import java.util.List;

@RestController()
@RequestMapping("/api/lokasi")
public class LokasiController {
    
    @Autowired
    private LokasiService service;

    @GetMapping
    public ResponseEntity<List<Lokasi>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Lokasi> save(@Valid @RequestBody Lokasi lokasi) {
        return ResponseEntity.ok(service.save(lokasi));
    }

    @PostMapping("/bulk")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Lokasi>> saveAll(@RequestBody List<Lokasi> lokasi) {
        return ResponseEntity.ok(service.saveAll(lokasi));
    }
    
}
