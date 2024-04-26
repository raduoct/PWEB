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
public class SegmentCalatorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long segmentCalatorie_id;

    private int nr_ord_calatorie;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "calatorie_id", referencedColumnName = "calatorie_id", nullable = false)
    private Calatorie calatorie;

    @JsonIgnore
    @OneToMany(mappedBy = "segmentCalatorie", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<TraseuStatie> statii;
}
