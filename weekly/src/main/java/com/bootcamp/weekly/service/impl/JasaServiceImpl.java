package com.bootcamp.weekly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.bootcamp.weekly.model.Jasa;
import com.bootcamp.weekly.repo.JasaRepository;
import com.bootcamp.weekly.service.JasaService;

@Service
public class JasaServiceImpl implements JasaService {

    @Autowired
    private JasaRepository repo;

    @Override
    public List<Jasa> getAll() throws IllegalStateException {

        var data = repo.findAll();
        if (data.isEmpty()) 
            throw new IllegalStateException("List is empty");
        return data;

    }

    @Override
    public Jasa getById(Long id) throws IllegalArgumentException, IllegalStateException {

        if (id == null || id <= 0)
            throw new IllegalArgumentException("id is required");
        var data = repo.findById(id);
        if (data.isEmpty())
            throw new IllegalStateException("Data not found or empty");
        return data.get();

    }

    @Override
    public Jasa save(Jasa jasa) throws IllegalArgumentException {

        jasaValidation(jasa);
        return repo.save(jasa);

    }

    public void jasaValidation(Jasa jasa) throws IllegalArgumentException {

        if (jasa == null 
                || jasa.getNama() == null 
                || jasa.getNama().isBlank()
                || jasa.getHarga() == null
                || jasa.getHarga() <= 0 
        ) {
            throw new IllegalArgumentException("jasa nama and/or harga is required");
        }

    }

    public void jasaInputValidation(Jasa jasa) throws IllegalArgumentException {

        if (jasa == null 
                || jasa.getId() == null
                || jasa.getId() <= 0
        ) {
            throw new IllegalArgumentException("jasa id is required");
        }

    }

}
