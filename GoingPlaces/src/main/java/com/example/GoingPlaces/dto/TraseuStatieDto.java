package com.example.GoingPlaces.dto;

import jakarta.validation.constraints.Min;

public class TraseuStatieDto {

    @Min(value = 0)
    private int nr_ord_traseu;

    @Min(value = 0)
    private Long traseu_id;

    @Min(value = 0)
    private Long statie_id;
}
