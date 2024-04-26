package com.example.GoingPlaces.service;

import com.example.GoingPlaces.repository.FeedbackRepository;
import com.example.GoingPlaces.repository.SegmentCalatorieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SegmentCalatorieService {
    private final SegmentCalatorieRepository segmentCalatorieRepository;
    private final ModelMapper modelMapper;
}
