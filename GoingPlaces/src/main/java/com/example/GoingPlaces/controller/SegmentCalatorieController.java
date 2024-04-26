package com.example.GoingPlaces.controller;

import com.example.GoingPlaces.service.CalatorieService;
import com.example.GoingPlaces.service.SegmentCalatorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/segmentCalatorie")
public class SegmentCalatorieController {
    private final SegmentCalatorieService segmentCalatorieService;
}
