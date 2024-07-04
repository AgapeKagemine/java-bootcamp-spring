package com.bootcamp.weekly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bootcamp.weekly.model.Jasa;
import com.bootcamp.weekly.model.Lokasi;
import com.bootcamp.weekly.model.Paket;
import com.bootcamp.weekly.model.PaketStatus;
import com.bootcamp.weekly.model.Pengiriman;
import com.bootcamp.weekly.repo.JasaRepository;
import com.bootcamp.weekly.repo.LokasiRepository;
import com.bootcamp.weekly.repo.PaketRepository;
import com.bootcamp.weekly.repo.PengirimanRepository;
import com.bootcamp.weekly.service.PengirimanService;

@Service
public class PengirimanServiceImpl implements PengirimanService {

    @Autowired
    private JasaRepository jasaRepo;
    
    @Autowired
    private LokasiRepository lokasiRepo;
    
    @Autowired
    private PaketRepository paketRepo;
    
    @Autowired
    private PengirimanRepository pengirimanRepo;

    LokasiServiceImpl ls = new LokasiServiceImpl();

    public List<Pengiriman> getAll() throws IllegalStateException {

        var data = pengirimanRepo.findAll();
        if (data.isEmpty()) 
            throw new IllegalStateException("List is empty");
        return data;

    }

    public Pengiriman getById(Long id) throws IllegalArgumentException, IllegalStateException {

        if (id == null || id < 1)
            throw new IllegalArgumentException("id is required"); 
        var data = pengirimanRepo.findById(id);
        if (data.isEmpty())
            throw new IllegalStateException("Data not found or empty");
        return data.get();

    }
    
    public List<Paket> getAllPacketReceived() throws IllegalStateException {

        var data = pengirimanRepo.findAll()
                .stream()
                .filter(f -> f.getIsReceived() == true)
                .map(f -> f.getPaket())
                .toList();
        if (data.isEmpty())
            throw new IllegalStateException("List is empty");
        return data;

    }

    public List<PaketStatus> getAllCheckedCheckpoint(String name) throws IllegalArgumentException, IllegalStateException {

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name is required");

        var data = pengirimanRepo.findAll()
                .stream()
                .filter(p -> (p.getCheckpoint()
                                .stream()
                                .anyMatch(L -> L.getAlamat()
                                                .equals(name)
                                ) && !p.getIsReceived()
                            )
                        )
                .map(f -> new PaketStatus(f.getPaket(), f.getIsReceived()))
                .collect(Collectors.toList());
        if (data.isEmpty())
            throw new IllegalStateException("List is empty");
        return data;

    }

    public Pengiriman updateLokasiPengiriman(Long id, Lokasi lokasi) throws IllegalArgumentException, IllegalStateException {

        if (id == null || id < 1)
            throw new IllegalArgumentException("id is required");

        ls.lokasiInputValidation(lokasi);

        Optional<Pengiriman> optionalPengiriman = pengirimanRepo.findById(id);
        if (optionalPengiriman.isEmpty()) 
            throw new IllegalStateException("Pengiriman not found");
        var pengiriman = optionalPengiriman.get();

        Optional<Lokasi> optionalLokasi = lokasiRepo.findById(lokasi.getId());
        if (optionalLokasi.isEmpty()) 
            throw new IllegalStateException("Lokasi not found");
        var newLokasi = optionalLokasi.get();
        
        pengiriman.setSingleCheckpoint(newLokasi);
        if(pengiriman.getCheckpoint().stream().anyMatch(f -> f.getId()
                                                                .equals(pengiriman.getPaket()
                                                                                    .getTujuan()
                                                                                    .getId()
                                                        )))
            pengiriman.setIsReceived(true);
        return pengirimanRepo.save(pengiriman);

    }

    public Pengiriman save(Pengiriman pengiriman) throws IllegalArgumentException, IllegalStateException {

        validasiPengiriman(pengiriman);

        Optional<Jasa> optionalJasa = jasaRepo.findById(pengiriman.getJasa().getId());
        if (optionalJasa.isEmpty())
            throw new IllegalStateException("Jasa not found");
        pengiriman.setJasa(optionalJasa.get());

        Optional<Paket> optionalPaket = paketRepo.findById(pengiriman.getPaket().getId());
        if (optionalPaket.isEmpty())
            throw new IllegalStateException("Paket not found");
        pengiriman.setPaket(optionalPaket.get());

        List<Lokasi> checkpoint = new ArrayList<>();
        for(var lokasi : pengiriman.getCheckpoint()) {
            var v = lokasiRepo.findById(lokasi.getId());
            if(v.isPresent()) checkpoint.add(v.get());
        }
        pengiriman.setCheckpoint(checkpoint);

        if(pengiriman.getCheckpoint().stream().anyMatch(f -> f.getId()
                                                                .equals(optionalPaket.get()
                                                                .getTujuan()
                                                                .getId()
                                                        )))
            pengiriman.setIsReceived(true);

        pengiriman.setHarga(optionalPaket.get().getBerat() * optionalJasa.get().getHarga());

        return pengirimanRepo.save(pengiriman);

    }

    public void validasiPengiriman(Pengiriman pengiriman) throws IllegalArgumentException, IllegalStateException {

        if (pengiriman.getCheckpoint().isEmpty())
            throw new IllegalStateException("Checkpoint is required [Hint: Array of Lokasi]");
        new JasaServiceImpl().jasaInputValidation(pengiriman.getJasa());
        new PaketServiceImpl().paketInputValidation(pengiriman.getPaket());
        for (var p : pengiriman.getCheckpoint()) {
            ls.lokasiInputValidation(p);
        }

    }

}
