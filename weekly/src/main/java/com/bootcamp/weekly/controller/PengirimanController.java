package com.bootcamp.weekly.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.weekly.model.Lokasi;
import com.bootcamp.weekly.model.Paket;
import com.bootcamp.weekly.model.PaketStatus;
import com.bootcamp.weekly.model.Pengiriman;
import com.bootcamp.weekly.service.PengirimanService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/pengiriman")
public class PengirimanController {
    
    @Autowired
    private PengirimanService service;

    @GetMapping
    public ResponseEntity<List<Pengiriman>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/received")
    public ResponseEntity<List<Paket>> getAllReceived() {   
        return ResponseEntity.ok(service.getAllPacketReceived());
    }

    @GetMapping("/checkpoint")
    public ResponseEntity<List<PaketStatus>> getAllCheckedCheckpoint(@Valid @RequestParam String nama) {
        return ResponseEntity.ok(service.getAllCheckedCheckpoint(nama));
    }

    @PostMapping
    public ResponseEntity<Pengiriman> save(@Valid @RequestBody Pengiriman pengiriman) {
        return ResponseEntity.ok(service.save(pengiriman));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Pengiriman> postMethodName(@Valid @PathVariable Long id, @Valid @RequestBody Lokasi lokasi) {
        return ResponseEntity.ok(service.updateLokasiPengiriman(id, lokasi));
    }
    
}
