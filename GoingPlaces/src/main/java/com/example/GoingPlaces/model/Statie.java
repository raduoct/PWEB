package com.example.GoingPlaces.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Statie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statie_id;

    private String nume;
    private double lat;
    private double lon;

    @JsonIgnore
    @OneToMany(mappedBy = "statie", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TraseuStatie> statie;

}
