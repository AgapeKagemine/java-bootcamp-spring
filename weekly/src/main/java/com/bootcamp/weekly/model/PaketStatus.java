package com.bootcamp.weekly.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaketStatus {

    public Paket paket;
    public Boolean isReceived;

}
