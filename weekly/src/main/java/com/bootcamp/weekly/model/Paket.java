package com.bootcamp.weekly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paket")
public class Paket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Valid
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Pengirim pengirim;

    @Valid
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Penerima penerima;

    @Valid
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Lokasi tujuan;

    @Column(nullable = false)
    private Float berat;

}
