package com.motos.motos.service;

import com.motos.motos.models.Motos;
import com.motos.motos.models.dto.MotosFullDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface MotosService {

    Page<Motos> getAllMotos(Pageable page, Specification<Motos> spec);

    List<Motos> saveMotos(List<Motos> motos);

    Motos updateMoto(Motos moto);

    void deleteMoto(UUID id);

    Page<MotosFullDto> getFullMotos(Pageable page, Specification<Motos> spec);
}
