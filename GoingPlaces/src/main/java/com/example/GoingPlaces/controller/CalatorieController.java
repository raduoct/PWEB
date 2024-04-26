package com.example.GoingPlaces.controller;

import com.example.GoingPlaces.service.CalatorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calatorie")
public class CalatorieController {
    private final CalatorieService calatorieService;
}
