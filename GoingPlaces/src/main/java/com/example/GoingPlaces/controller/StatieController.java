package com.example.GoingPlaces.controller;

import com.example.GoingPlaces.dto.StatieDto;
import com.example.GoingPlaces.dto.TraseuDto;
import com.example.GoingPlaces.model.Statie;
import com.example.GoingPlaces.model.Traseu;
import com.example.GoingPlaces.service.CalatorieService;
import com.example.GoingPlaces.service.StatieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statie")
public class StatieController {
    private final StatieService statieService;

    @PostMapping("/")
    public ResponseEntity<Statie> registerStatie(@RequestBody StatieDto StatieDto) {
        Statie statie = statieService.registerStatie(StatieDto);
        return ResponseEntity.ok(statie);
    }

    @GetMapping("/")
    public ResponseEntity<List<Statie>> getAllStatii() {
        List<Statie> statii = statieService.getAllStatii();
        return ResponseEntity.ok(statii);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Statie> getStatieById(@PathVariable Long id) {

        Statie statie= statieService.getStatieById(id);
        return ResponseEntity.ok(statie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Statie> updateStatie(@PathVariable Long id, @RequestBody StatieDto StatieDto) {
        Statie updatedStatie = statieService.updateStatie(id, StatieDto);
        return ResponseEntity.ok(updatedStatie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatie(@PathVariable Long id) {
        statieService.deleteStatie(id);
        return ResponseEntity.ok().build();
    }
}
