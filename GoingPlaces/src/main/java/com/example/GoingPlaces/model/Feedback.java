package com.example.GoingPlaces.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedback_id;

    private double rating;
    private String comment;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calatorie_id", referencedColumnName = "calatorie_id")
    private Calatorie calatorie;
}
