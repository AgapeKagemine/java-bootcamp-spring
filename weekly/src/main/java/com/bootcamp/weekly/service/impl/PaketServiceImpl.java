package com.bootcamp.weekly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.bootcamp.weekly.model.Paket;
import com.bootcamp.weekly.repo.PaketRepository;
import com.bootcamp.weekly.service.PaketService;

@Service
public class PaketServiceImpl implements PaketService {

    @Autowired
    private PaketRepository repo;

    @Override
    public List<Paket> getAll() throws IllegalStateException{

        var data = repo.findAll();
        if (data.isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return data;

    }

    @Override
    public Paket getById(Long id) throws IllegalArgumentException, IllegalStateException {

        if (id == null || id <= 0)
            throw new IllegalArgumentException("id is required");
        var data = repo.findById(id);
        if (data.isEmpty())
            throw new IllegalArgumentException("Data not found or empty");
        return data.get();

    }

    @Override
    public Paket save(Paket paket) throws IllegalArgumentException {

        paketValidation(paket);
        new LokasiServiceImpl().lokasiInputValidation(paket.getTujuan());
        new PengirimServiceImpl().pengirimInputValidation(paket.getPengirim());
        new PenerimaServiceImpl().penerimaInputValidation(paket.getPenerima());
        return repo.save(paket);

    }

    public void paketValidation(Paket paket) throws IllegalArgumentException {

        if (paket == null 
                || paket.getBerat() == null
                || paket.getBerat() < 0
        ) {
            throw new IllegalArgumentException("paket berat is required");
        }

    }

    public void paketInputValidation(Paket paket) throws IllegalArgumentException {

        if (paket == null
                || paket.getId() == null
                || paket.getId() <= 0
        ) {
            throw new IllegalArgumentException("paket id is required");
        }

    }
    
}
