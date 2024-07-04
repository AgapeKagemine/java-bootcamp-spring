package com.bootcamp.weekly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.bootcamp.weekly.model.Lokasi;
import com.bootcamp.weekly.repo.LokasiRepository;
import com.bootcamp.weekly.service.LokasiService;

@Service
public class LokasiServiceImpl implements LokasiService {

    @Autowired
    private LokasiRepository repo;

    @Override
    public List<Lokasi> getAll() throws IllegalStateException {

        var data = repo.findAll();
        if (data.isEmpty()) 
            throw new IllegalStateException("List is empty");
        return data;

    }

    @Override
    public Lokasi getById(Long id) throws IllegalArgumentException, IllegalStateException {

        if (id == null || id <= 0)
            throw new IllegalArgumentException("id is required");
        var data = repo.findById(id);
        if (data.isEmpty())
            throw new IllegalStateException("Data not found or empty");
        return data.get();

    }

    @Override
    public Lokasi save(Lokasi lokasi) throws IllegalArgumentException {

        lokasiValidation(lokasi);
        return repo.save(lokasi);

    }

    @Override
    public List<Lokasi> saveAll(List<Lokasi> lokasi) throws IllegalArgumentException {

        if (lokasi.isEmpty())
            throw new IllegalArgumentException("List is empty");
        for (var l : lokasi) lokasiValidation(l);
        return repo.saveAll(lokasi);

    }

    public void lokasiValidation(Lokasi lokasi) throws IllegalArgumentException {

        if (lokasi == null 
                || lokasi.getNama() == null 
                || lokasi.getNama().isBlank()
        ) {
            throw new IllegalArgumentException("lokasi nama is required");
        }

    }

    public void lokasiInputValidation(Lokasi lokasi) throws IllegalArgumentException {

        if (lokasi == null 
                || lokasi.getId() == null
                || lokasi.getId() <= 0
        ) {
            throw new IllegalArgumentException("lokasi id is required");
        }

    }
}
