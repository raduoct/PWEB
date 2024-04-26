package com.example.GoingPlaces.controller;

import com.example.GoingPlaces.dto.TraseuDto;
import com.example.GoingPlaces.dto.UserDto;
import com.example.GoingPlaces.model.Traseu;
import com.example.GoingPlaces.model.User;
import com.example.GoingPlaces.service.StatieService;
import com.example.GoingPlaces.service.TraseuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/traseu")
public class TraseuController {
    private final TraseuService traseuService;

    @PostMapping("/")
    public ResponseEntity<Traseu> registerTraseu(@RequestBody TraseuDto traseuDto) {
        Traseu traseu = traseuService.registerTraseu(traseuDto);
        return ResponseEntity.ok(traseu);
    }

    @GetMapping("/")
    public ResponseEntity<List<Traseu>> getAllTrasee() {
        List<Traseu> trasee = traseuService.getAllTrasee();
        return ResponseEntity.ok(trasee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Traseu> getTraseuById(@PathVariable Long id) {

        Traseu traseu= traseuService.getTraseuById(id);
        return ResponseEntity.ok(traseu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Traseu> updateTraseu(@PathVariable Long id, @RequestBody TraseuDto traseuDto) {
        Traseu updatedTraseu = traseuService.updateTraseu(id, traseuDto);
        return ResponseEntity.ok(updatedTraseu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraseu(@PathVariable Long id) {
        traseuService.deleteTraseu(id);
        return ResponseEntity.ok().build();
    }
}
