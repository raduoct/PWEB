package com.example.GoingPlaces.service;

import com.example.GoingPlaces.dto.StatieDto;
import com.example.GoingPlaces.model.Statie;
import com.example.GoingPlaces.model.Traseu;
import com.example.GoingPlaces.repository.SegmentCalatorieRepository;
import com.example.GoingPlaces.repository.StatieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatieService {
    private final StatieRepository statieRepository;
    private final ModelMapper modelMapper;

    public Statie registerStatie(StatieDto statieDto) {
        if (statieRepository.findByNume(statieDto.getNume()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Statie with nume " + statieDto.getNume() + " already exists.");
        }
        Statie statie = modelMapper.map(statieDto, Statie.class);
        return statieRepository.save(statie);
    }

    public List<Statie> getAllStatii() {
        return statieRepository.findAll();
    }

    public Statie getStatieById(Long id) {
        return statieRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Statie not found."));
    }

    public Statie updateStatie(Long id, StatieDto statieDto) {
        Statie statie = statieRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Statie not found."));

        if (statieDto.getNume() != null && !statieDto.getNume().isEmpty()){
            if(statieRepository.findByNume(statieDto.getNume()).isEmpty() || !statieDto.getNume().equals(statie.getNume()))
                statie.setNume(statieDto.getNume());
            else
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Statie with nume " + statieDto.getNume() + " already exists.");
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad nume.");

        return statieRepository.save(statie);
    }

    public void deleteStatie(Long id) {
        if (!statieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Statie not found.");
        }
        statieRepository.deleteById(id);
    }
}
