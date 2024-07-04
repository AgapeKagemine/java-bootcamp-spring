package com.bootcamp.weekly.service;

import java.util.List;

import com.bootcamp.weekly.model.Lokasi;

public interface LokasiService {
    public List<Lokasi> getAll() throws IllegalStateException;
    public Lokasi getById(Long id) throws IllegalArgumentException, IllegalStateException;
    public Lokasi save(Lokasi lokasi) throws IllegalArgumentException;
    public List<Lokasi> saveAll(List<Lokasi> lokasi) throws IllegalArgumentException;
}
