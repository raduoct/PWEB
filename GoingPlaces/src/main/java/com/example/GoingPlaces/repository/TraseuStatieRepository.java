package com.example.GoingPlaces.repository;

import com.example.GoingPlaces.model.TraseuStatie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraseuStatieRepository extends JpaRepository<TraseuStatie, Long> {
}