package com.bootcamp.weekly.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bootcamp.weekly.model.Lokasi;
import com.bootcamp.weekly.model.Paket;
import com.bootcamp.weekly.model.PaketStatus;
import com.bootcamp.weekly.model.Pengiriman;
import com.bootcamp.weekly.service.PengirimanService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class PengirimanControllerTest {
    
    @Mock 
    private PengirimanService pengirimanService;

    @InjectMocks
    private PengirimanController pengirimanController;

    @Test
    public void testGetAll() {
        List<Pengiriman> mockList = Arrays.asList(new Pengiriman(), new Pengiriman());
        
        when(pengirimanService.getAll()).thenReturn(mockList);

        ResponseEntity<List<Pengiriman>> responseEntity = pengirimanController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockList, responseEntity.getBody());
    }

    @Test
    public void testGetAllReceived() {
        List<Pengiriman> mockList = Arrays.asList(new Pengiriman(), new Pengiriman());
        List<Paket> mockTrue = Arrays.asList(mockList.get(0).getPaket());

        when(pengirimanService.getAllPacketReceived()).thenReturn(mockTrue);

        ResponseEntity<List<Paket>> responseEntity = pengirimanController.getAllReceived();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockTrue, responseEntity.getBody());
    }

    @Test
    public void testGetAllCheckedCheckpoint() {
        List<PaketStatus> mockList = Arrays.asList(new PaketStatus(), new PaketStatus());
        String nama = "example";
        when(pengirimanService.getAllCheckedCheckpoint(nama)).thenReturn(mockList);

        ResponseEntity<List<PaketStatus>> responseEntity = pengirimanController.getAllCheckedCheckpoint(nama);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockList, responseEntity.getBody());
    }

    @Test
    public void testSave() {
        Pengiriman mockPengiriman = new Pengiriman();
        when(pengirimanService.save(any(Pengiriman.class))).thenReturn(mockPengiriman);

        ResponseEntity<Pengiriman> responseEntity = pengirimanController.save(new Pengiriman());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPengiriman, responseEntity.getBody());
    }

    @Test
    public void testUpdateLokasiPengiriman() {
        Long id = 1L;
        Lokasi mockLokasi = new Lokasi();
        Pengiriman mockPengiriman = new Pengiriman();
        when(pengirimanService.updateLokasiPengiriman(eq(id), any(Lokasi.class))).thenReturn(mockPengiriman);

        ResponseEntity<Pengiriman> responseEntity = pengirimanController.postMethodName(id, mockLokasi);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPengiriman, responseEntity.getBody());
    }

}
