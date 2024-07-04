package com.bootcamp.weekly.service;

import java.util.List;

import com.bootcamp.weekly.model.Paket;

public interface PaketService {
    public List<Paket> getAll() throws IllegalStateException;
    public Paket getById(Long id) throws IllegalArgumentException, IllegalStateException;
    public Paket save(Paket paket) throws IllegalArgumentException;
}
