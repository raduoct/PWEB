package com.example.GoingPlaces.repository;

import com.example.GoingPlaces.model.Statie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatieRepository extends JpaRepository<Statie, Long> {
    Optional<Statie> findByNume(String nume);
}
