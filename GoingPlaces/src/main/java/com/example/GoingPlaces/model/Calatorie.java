package com.example.GoingPlaces.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Calatorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calatorie_id;

    private double origin_lat;
    private double origin_lon;
    private double destination_lat;
    private double destination_lon;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "calatorie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Feedback feedback;

    @JsonIgnore
    @OneToMany(mappedBy = "calatorie", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SegmentCalatorie> segments;
}
