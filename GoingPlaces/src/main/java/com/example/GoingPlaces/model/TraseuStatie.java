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
public class TraseuStatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long traseuStatie_id;

    private int nr_ord_traseu;
//    private Long idTraseu;
//    private Long idStatie;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "segmentCalatorie_id", referencedColumnName = "segmentCalatorie_id", nullable = false)
    private SegmentCalatorie segmentCalatorie;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Traseu_id", referencedColumnName = "traseu_id", nullable = false)
    private Traseu traseu;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Statie_id", referencedColumnName = "statie_id", nullable = false)
    private Statie statie;
}
