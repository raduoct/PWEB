package com.example.GoingPlaces.repository;

import com.example.GoingPlaces.model.Calatorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalatorieRepository extends JpaRepository<Calatorie, Long> {
}
