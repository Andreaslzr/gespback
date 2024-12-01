package com.motos.motos.repository;

import com.motos.motos.models.Parts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PartsRepository
        extends JpaRepository<Parts, UUID>, JpaSpecificationExecutor<Parts> {

    List<Parts> findAll(Specification<Parts> spec);

    Page<Parts> findAll(Specification<Parts> spec, Pageable pageable);
}
