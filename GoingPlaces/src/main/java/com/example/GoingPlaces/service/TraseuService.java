package com.example.GoingPlaces.service;

import com.example.GoingPlaces.dto.TraseuDto;
import com.example.GoingPlaces.model.Traseu;
import com.example.GoingPlaces.model.User;
import com.example.GoingPlaces.repository.StatieRepository;
import com.example.GoingPlaces.repository.TraseuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.transform.TransformerException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TraseuService {
    private final TraseuRepository traseuRepository;
    private final ModelMapper modelMapper;

    public Traseu registerTraseu(TraseuDto traseuDto) {
        if (traseuRepository.findByNume(traseuDto.getNume()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Traseu with nume " + traseuDto.getNume() + " already exists.");
        }
        Traseu traseu = modelMapper.map(traseuDto, Traseu.class);
        return traseuRepository.save(traseu);
    }

    public List<Traseu> getAllTrasee() {
        return traseuRepository.findAll();
    }

    public Traseu getTraseuById(Long id) {
        return traseuRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Traseu not found."));
    }

    public Traseu updateTraseu(Long id, TraseuDto traseuDto) {
        // Fetch the existing traseu from the database
        Traseu traseu = traseuRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Traseu not found."));

        // Validate the new name
        String newName = traseuDto.getNume();
        if (newName == null || newName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad nume.");
        }

        // Check if the new name already exists in another record (excluding the current traseu)
        boolean nameExists = traseuRepository.findByNume(newName)
                .map(existingTraseu -> !existingTraseu.getTraseu_id().equals(traseu.getTraseu_id()))
                .orElse(false);

        if (nameExists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Traseu with nume " + newName + " already exists.");
        }

        // Update the name if it's valid and does not exist in other records
        traseu.setNume(newName);
        return traseuRepository.save(traseu);
    }


    public void deleteTraseu(Long id) {
        if (!traseuRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Traseu not found.");
        }
        traseuRepository.deleteById(id);
    }
}