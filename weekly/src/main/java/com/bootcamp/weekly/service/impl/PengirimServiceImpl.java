package com.bootcamp.weekly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.bootcamp.weekly.model.Pengirim;
import com.bootcamp.weekly.repo.PengirimRepository;
import com.bootcamp.weekly.service.PengirimService;

@Service
public class PengirimServiceImpl implements PengirimService {

    @Autowired
    private PengirimRepository repo;

    @Override
    public List<Pengirim> getAll() throws IllegalStateException {

        var data = repo.findAll();
        if (data.isEmpty())
            throw new IllegalStateException("List is empty");
        return data;

    }

    @Override
    public Pengirim getById(Long id) throws IllegalArgumentException, IllegalStateException {

        if (id == null || id <= 0)
            throw new IllegalArgumentException("id is required");
        var data = repo.findById(id);
        if (data.isEmpty())
            throw new IllegalStateException("Data not found or empty");
        return data.get();

    }

    @Override
    public Pengirim save(Pengirim pengirim) throws IllegalArgumentException {

        pengirimValidation(pengirim);
        return repo.findById(repo.save(pengirim).getId()).get();

    }

    public void pengirimValidation(Pengirim pengirim) throws IllegalArgumentException {

        if (pengirim == null
                || pengirim.getNama() == null
                || pengirim.getNama().isBlank()
                || pengirim.getTelepon() == null
                || pengirim.getTelepon().isBlank()
                || pengirim.getTelepon().length() < 10 
                || pengirim.getTelepon().length() > 13
        ) {
            throw new IllegalArgumentException("pengirim nama and/or telepon is required");
        }

    }

    public void pengirimInputValidation(Pengirim pengirim) throws IllegalArgumentException {

        if (pengirim == null
                || pengirim.getId() == null
                || pengirim.getId() <= 0
        ) {
            throw new IllegalArgumentException("pengirim id is required");
        }

    }
}
