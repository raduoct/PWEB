package com.example.GoingPlaces.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

    @Min(value = 0)
    private double rating;

    @NotBlank
    @Size(max = 400)
    private String comment;
}
