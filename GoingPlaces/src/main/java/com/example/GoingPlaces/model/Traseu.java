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
public class Traseu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long traseu_id;

    private String nume;

    @JsonIgnore
    @OneToMany(mappedBy = "traseu", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TraseuStatie> traseu;
}
