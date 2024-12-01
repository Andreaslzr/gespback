package com.motos.motos.repository;

import com.motos.motos.models.Motos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MotosRepository extends JpaRepository<Motos, UUID>,
        JpaSpecificationExecutor<Motos> {

    Page<Motos> findAll(Specification<Motos> spec, Pageable pageable);
}


