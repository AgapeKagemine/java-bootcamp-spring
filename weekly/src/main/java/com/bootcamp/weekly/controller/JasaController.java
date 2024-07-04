package com.bootcamp.weekly.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bootcamp.weekly.model.Jasa;
import com.bootcamp.weekly.service.JasaService;

import jakarta.validation.Valid;

import java.util.List;

@RestController()
@RequestMapping("/api/jasa")
public class JasaController {
    
    @Autowired
    private JasaService service;

    @GetMapping
    public ResponseEntity<List<Jasa>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Jasa> save(@Valid @RequestBody Jasa jasa) {
        return ResponseEntity.ok(service.save(jasa));
    }
    
}
