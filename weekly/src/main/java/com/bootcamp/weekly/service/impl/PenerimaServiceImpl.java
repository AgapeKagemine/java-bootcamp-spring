package com.bootcamp.weekly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.bootcamp.weekly.model.Penerima;
import com.bootcamp.weekly.repo.PenerimaRepository;
import com.bootcamp.weekly.service.PenerimaService;

@Service
public class PenerimaServiceImpl implements PenerimaService {

    @Autowired
    private PenerimaRepository repo;

    @Override
    public List<Penerima> getAll() throws IllegalStateException {

        var data = repo.findAll();
        if (data.isEmpty())
            throw new IllegalStateException("List is empty");
        return data;

    }

    @Override
    public Penerima getById(Long id) throws IllegalArgumentException, IllegalStateException {

        if (id == null || id <= 0)
            throw new IllegalArgumentException("id is required");
        var data = repo.findById(id);
        if (data.isEmpty())
            throw new IllegalStateException("Data not found or empty");
        return data.get();

    }

    @Override
    public Penerima save(Penerima penerima) throws IllegalArgumentException {

        penerimaValidation(penerima);
        return repo.save(penerima);

    }

    public void penerimaValidation(Penerima penerima) throws IllegalArgumentException {

        if (penerima == null
                || penerima.getNama() == null
                || penerima.getNama().isBlank()
                || penerima.getTelepon() == null
                || penerima.getTelepon().isBlank()
                || penerima.getTelepon().length() < 10 
                || penerima.getTelepon().length() > 13
        ) {
            throw new IllegalArgumentException("penerima nama and/or telepon is required");
        }
        
    }

    public void penerimaInputValidation(Penerima penerima) throws IllegalArgumentException {

        if (penerima == null
                || penerima.getId() == null
                || penerima.getId() <= 0
        ) {
            throw new IllegalArgumentException("penerima id is required");
        }
        
    }
}
