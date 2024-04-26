package com.example.GoingPlaces.repository;

import com.example.GoingPlaces.model.SegmentCalatorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentCalatorieRepository extends JpaRepository<SegmentCalatorie, Long> {
}
