package com.example.GoingPlaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatieDto {

    @NotBlank
    @Size(max = 200)
    private String nume;

    private double lat;

    private double lon;
}
