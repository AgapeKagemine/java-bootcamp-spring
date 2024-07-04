package com.bootcamp.weekly.service;

import java.util.List;

import com.bootcamp.weekly.model.Jasa;

public interface JasaService {
    public List<Jasa> getAll() throws IllegalStateException;
    public Jasa getById(Long id) throws IllegalArgumentException, IllegalStateException;
    public Jasa save(Jasa jasa) throws IllegalArgumentException;
}
