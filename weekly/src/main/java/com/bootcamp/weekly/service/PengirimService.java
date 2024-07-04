package com.bootcamp.weekly.service;

import java.util.List;

import com.bootcamp.weekly.model.Pengirim;

public interface PengirimService {
    public List<Pengirim> getAll() throws IllegalStateException;
    public Pengirim getById(Long id) throws IllegalArgumentException, IllegalStateException;
    public Pengirim save(Pengirim pengirim) throws IllegalArgumentException;
}
