package com.example.GoingPlaces.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalatorieDto {
    private double origin_lat;

    private double origin_lon;

    private double destination_lat;

    private double destination_lon;

    @NotNull
    private LocalTime departureTime;

    @NotNull
    private LocalTime arrivalTime;
}
