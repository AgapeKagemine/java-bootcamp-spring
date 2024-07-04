package com.bootcamp.weekly.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pengiriman")
public class Pengiriman {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Jasa jasa;

    @ManyToOne
    private Paket paket;

    private Float harga = 0.0f;

    @ManyToMany(cascade = {
        CascadeType.PERSIST, 
        CascadeType.MERGE
    })
    @JoinTable
    private List<Lokasi> checkpoint;

    private Boolean IsReceived = false;

    public void setSingleCheckpoint(Lokasi lokasi) {
        this.checkpoint.add(lokasi);
    }

}
