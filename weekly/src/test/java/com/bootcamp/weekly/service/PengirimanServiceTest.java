package com.bootcamp.weekly.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import java.util.Collections;

import org.hibernate.mapping.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.bootcamp.weekly.model.Jasa;
import com.bootcamp.weekly.model.Lokasi;
import com.bootcamp.weekly.model.Paket;
import com.bootcamp.weekly.model.PaketStatus;
import com.bootcamp.weekly.model.Penerima;
import com.bootcamp.weekly.model.Pengirim;
import com.bootcamp.weekly.model.Pengiriman;
import com.bootcamp.weekly.repo.JasaRepository;
import com.bootcamp.weekly.repo.LokasiRepository;
import com.bootcamp.weekly.repo.PaketRepository;
import com.bootcamp.weekly.repo.PengirimanRepository;
import com.bootcamp.weekly.service.impl.LokasiServiceImpl;
import com.bootcamp.weekly.service.impl.PaketServiceImpl;
import com.bootcamp.weekly.service.impl.PengirimanServiceImpl;

public class PengirimanServiceTest {
    
    @Mock
    private PengirimanRepository pengirimanRepo;

    @Mock
    private LokasiRepository lokasiRepo;

    @Mock
    private PaketRepository paketRepo;

    @Mock
    private JasaRepository jasaRepo;

    @InjectMocks
    private PengirimanServiceImpl pengirimanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pengirimanService = new PengirimanServiceImpl();
        ReflectionTestUtils.setField(pengirimanService, "pengirimanRepo", pengirimanRepo);
        ReflectionTestUtils.setField(pengirimanService, "lokasiRepo", lokasiRepo);
        ReflectionTestUtils.setField(pengirimanService, "paketRepo", paketRepo);
        ReflectionTestUtils.setField(pengirimanService, "jasaRepo", jasaRepo);
    }

    @Test
    void testGetAll() {
        List<Pengiriman> testData = Arrays.asList(
            new Pengiriman(),
            new Pengiriman()
        );
        when(pengirimanRepo.findAll()).thenReturn(testData);

        assertDoesNotThrow(() -> {
            List<Pengiriman> result = pengirimanService.getAll();
            assertEquals(testData.size(), result.size());
        });
    }

    @Test
    void testGetAllEmptyList() {
        when(pengirimanRepo.findAll()).thenReturn(Collections.emptyList());

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            pengirimanService.getAll();
        });

        assertEquals("List is empty", thrown.getMessage());
    }

    @Test
    void testGetByIdValidId() {
        Long validId = 1L;
        Pengiriman mockPengiriman = new Pengiriman();
        Optional<Pengiriman> optionalPengiriman = Optional.of(mockPengiriman);
        when(pengirimanRepo.findById(validId)).thenReturn(optionalPengiriman);

        assertDoesNotThrow(() -> {
            Pengiriman result = pengirimanService.getById(validId);
            assertEquals(mockPengiriman, result);
        });
    }

    @Test
    void testGetByIdNullId() {
        Long nullId = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            pengirimanService.getById(nullId);
        });

        assertEquals("id is required", thrown.getMessage());
    }

    // @Test
    // void testGetByIdInvalidId() {
    //     Long invalidId = 0L;
    //     when(pengirimanRepo.findById(invalidId)).thenReturn(Optional.empty());

    //     IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
    //         pengirimanService.getById(invalidId);
    //     });

    //     assertEquals("Data not found or empty", thrown.getMessage());
    // }

    @Test
    void testGetAllPacketReceived() {
        List<Pengiriman> testData = Arrays.asList(
            new Pengiriman(),
            new Pengiriman()
        );
        testData.get(0).setIsReceived(true);
        when(pengirimanRepo.findAll()).thenReturn(testData);

        assertDoesNotThrow(() -> {
            List<Paket> result = pengirimanService.getAllPacketReceived();
            assertEquals(1, result.size());
        });
    }

    @Test
    void testGetAllPacketReceivedEmptyList() {
        when(pengirimanRepo.findAll()).thenReturn(Collections.emptyList());

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            pengirimanService.getAllPacketReceived();
        });

        assertEquals("List is empty", thrown.getMessage());
    }

    // @Test
    // void testGetAllCheckedCheckpoint() {
    //     String name = "CheckpointName";
    //     Lokasi checkpoint = new Lokasi();
    //     Pengiriman pengiriman1 = new Pengiriman();
    //     pengiriman1.getCheckpoint().add(checkpoint);
    //     Pengiriman pengiriman2 = new Pengiriman();
    //     pengiriman2.getCheckpoint().add(new Lokasi());

    //     List<Pengiriman> testData = Arrays.asList(pengiriman1, pengiriman2);
    //     when(pengirimanRepo.findAll()).thenReturn(testData);

    //     assertDoesNotThrow(() -> {
    //         List<PaketStatus> result = pengirimanService.getAllCheckedCheckpoint(name);
    //         assertEquals(1, result.size());
    //         assertEquals(pengiriman1.getPaket(), result.get(0).getPaket());
    //         assertFalse(result.get(0).getIsReceived());
    //     });
    // }

    @Test
    void testGetAllCheckedCheckpointEmptyList() {
        String name = "CheckpointName";
        when(pengirimanRepo.findAll()).thenReturn(Collections.emptyList());

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            pengirimanService.getAllCheckedCheckpoint(name);
        });

        assertEquals("List is empty", thrown.getMessage());
    }

    @Test
    void testGetAllCheckedCheckpointNullName() {
        String name = null;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            pengirimanService.getAllCheckedCheckpoint(name);
        });

        assertEquals("name is required", thrown.getMessage());
    }

    // @Test
    // void testUpdateLokasiPengiriman() {
    //     Long pengirimanId = 1L;
    //     Lokasi lokasi = new Lokasi();

    //     Pengiriman pengiriman = new Pengiriman();
    //     Optional<Pengiriman> optionalPengiriman = Optional.of(pengiriman);
    //     when(pengirimanRepo.findById(pengirimanId)).thenReturn(optionalPengiriman);

    //     Optional<Lokasi> optionalLokasi = Optional.of(lokasi);
    //     when(lokasiRepo.findById(lokasi.getId())).thenReturn(optionalLokasi);

    //     assertDoesNotThrow(() -> {
    //         Pengiriman result = pengirimanService.updateLokasiPengiriman(pengirimanId, lokasi);
    //         assertEquals(lokasi, result.getCheckpoint().getLast());
    //         assertTrue(result.getIsReceived());
    //     });
    // }

    @Test
    void testUpdateLokasiPengirimanInvalidId() {
        Long invalidId = 0L;
        Lokasi lokasi = new Lokasi();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            pengirimanService.updateLokasiPengiriman(invalidId, lokasi);
        });

        assertEquals("id is required", thrown.getMessage());
    }

    @Test
    void testSave() {
        Jasa jasa = new Jasa(1L, "asdf", 1000f);
        Pengirim pengirim = new Pengirim(1L, "asdf", "081283072977");
        Penerima penerima = new Penerima(1L, "asdf", "081283072977");
        Lokasi lokasi = new Lokasi(1L, "asdf", "<asdf-00001>");
        Paket paket = new Paket(1L, pengirim, penerima, lokasi, 5.0f);
        List<Lokasi> checkpoint = Arrays.asList(
            new Lokasi(1L, "asdf", "<asdf-00001>"),
            new Lokasi(2L, "asdf", "<asdf-00002>")
        );
        Pengiriman pengiriman = new Pengiriman(1L, jasa, paket, 5000.0f, checkpoint, true);

        when(jasaRepo.findById(pengiriman.getJasa().getId())).thenReturn(Optional.of(jasa));
        when(paketRepo.findById(pengiriman.getPaket().getId())).thenReturn(Optional.of(paket));
        when(lokasiRepo.findById(lokasi.getId())).thenReturn(Optional.of(lokasi));
        when(pengirimanRepo.save(any())).thenReturn(pengiriman);

        assertDoesNotThrow(() -> {
            Pengiriman result = pengirimanService.save(pengiriman);
            assertNotNull(result);
        });
    }

    // @Test
    // void testSaveJasaNotFound() {
    //     Pengiriman pengiriman = new Pengiriman();

    //     when(jasaRepo.findById(pengiriman.getJasa().getId())).thenReturn(Optional.empty());

    //     IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
    //         pengirimanService.save(pengiriman);
    //     });

    //     assertEquals("Jasa not found", thrown.getMessage());
    // }

    // @Test
    // void testSavePaketNotFound() {
    //     Pengiriman pengiriman = new Pengiriman();

    //     when(pengiriman.getJasa().getId()).thenReturn(null);
    //     when(pengiriman.getPaket().getId()).thenReturn(null);

    //     IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
    //         pengirimanService.save(pengiriman);
    //     });

    //     assertEquals("Paket not found", thrown.getMessage());
    // }

}
