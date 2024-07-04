package com.bootcamp.weekly.service;

import java.util.List;

import com.bootcamp.weekly.model.Penerima;

public interface PenerimaService {
    public List<Penerima> getAll() throws IllegalStateException;
    public Penerima getById(Long id) throws IllegalArgumentException, IllegalStateException;
    public Penerima save(Penerima penerima) throws IllegalArgumentException;
}
