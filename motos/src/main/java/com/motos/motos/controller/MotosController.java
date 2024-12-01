package com.motos.motos.controller;

import com.motos.motos.service.MotosService;
import com.motos.motos.models.Motos;
import com.motos.motos.models.dto.MotosDto;
import com.motos.motos.models.dto.MotosFullDto;
import com.motos.motos.models.dto.UpdateMotosDto;
import com.motos.motos.service.MotosService;
import com.motos.motos.specifications.MotosSpecification;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/motos")
@CrossOrigin(origins = "*")
public class MotosController {

    @Autowired
    MotosService motosService;

    @GetMapping
    public ResponseEntity<Page<Motos>> getAllRobots(
                                        Pageable pageable,
                                        MotosSpecification.MotosSpec spec ) {
        return ResponseEntity.ok(
                motosService.getAllMotos(pageable,spec)
        );
    }

    @GetMapping("/full")
    public ResponseEntity<Page<MotosFullDto>> getFullRobots(
                                        Pageable pageable,
                                        MotosSpecification.MotosSpec spec ) {
        return ResponseEntity.ok(
                motosService.getFullMotos(pageable,spec)
        );
    }

    @PostMapping
    public ResponseEntity<List<Motos>> addNewRobot(@RequestBody @Valid List<MotosDto> motos) {

        List<Motos> motosList = motos.stream().map(MotosDto::mapToMoto).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(
                motosService.saveMotos(motosList));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Motos> updateMoto(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateMotosDto moto) {

        Motos convertedMoto = moto.mapToMoto();
        convertedMoto.setId(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                motosService.updateMoto(convertedMoto));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteMoto(@PathVariable UUID id) {
        motosService.deleteMoto(id);
    }

}
