package com.example.GoingPlaces.repository;

import com.example.GoingPlaces.model.Traseu;
import com.example.GoingPlaces.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraseuRepository extends JpaRepository<Traseu, Long> {
    Optional<Traseu> findByNume(String nume);
}
