package com.example.GoingPlaces.service;

import com.example.GoingPlaces.dto.TraseuStatieDto;
import com.example.GoingPlaces.model.TraseuStatie;
import com.example.GoingPlaces.model.User;
import com.example.GoingPlaces.repository.TraseuRepository;
import com.example.GoingPlaces.repository.TraseuStatieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraseuStatieService {
    private final TraseuStatieRepository traseuStatieRepository;
    private final ModelMapper modelMapper;
}