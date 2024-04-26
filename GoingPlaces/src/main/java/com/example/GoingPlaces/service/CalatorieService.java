package com.example.GoingPlaces.service;

import com.example.GoingPlaces.repository.CalatorieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalatorieService {
    private final CalatorieRepository calatorieRepository;
    private final ModelMapper modelMapper;
}