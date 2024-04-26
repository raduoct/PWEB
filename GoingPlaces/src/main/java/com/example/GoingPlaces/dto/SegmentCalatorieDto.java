package com.example.GoingPlaces.dto;

import jakarta.validation.constraints.Min;

public class SegmentCalatorieDto {

    @Min(value = 0)
    private int nr_ord_calatorie;
}
