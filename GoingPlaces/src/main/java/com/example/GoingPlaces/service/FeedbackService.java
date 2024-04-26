package com.example.GoingPlaces.service;

import com.example.GoingPlaces.repository.CalatorieRepository;
import com.example.GoingPlaces.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;
}