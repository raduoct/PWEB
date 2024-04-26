package com.example.GoingPlaces.controller;

import com.example.GoingPlaces.dto.TraseuStatieDto;
import com.example.GoingPlaces.model.TraseuStatie;
import com.example.GoingPlaces.service.TraseuStatieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/traseuStatie")
public class TraseuStatieController {
    private final TraseuStatieService traseuStatieService;
}