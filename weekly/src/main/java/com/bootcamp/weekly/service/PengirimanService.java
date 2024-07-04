package com.bootcamp.weekly.service;

import java.util.List;

import com.bootcamp.weekly.model.Lokasi;
import com.bootcamp.weekly.model.Paket;
import com.bootcamp.weekly.model.PaketStatus;
import com.bootcamp.weekly.model.Pengiriman;

public interface PengirimanService {
    public List<Pengiriman> getAll() throws IllegalStateException;
    public List<Paket> getAllPacketReceived() throws IllegalStateException;
    public Pengiriman getById(Long id) throws IllegalArgumentException, IllegalStateException;
    public List<PaketStatus> getAllCheckedCheckpoint(String name) throws IllegalArgumentException, IllegalStateException;
    public Pengiriman updateLokasiPengiriman(Long id, Lokasi lokasi) throws IllegalArgumentException, IllegalStateException;
    public Pengiriman save(Pengiriman pengirimamn) throws IllegalArgumentException;
}
