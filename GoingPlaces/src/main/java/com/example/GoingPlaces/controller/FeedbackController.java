package com.example.GoingPlaces.controller;

import com.example.GoingPlaces.service.CalatorieService;
import com.example.GoingPlaces.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
}
