package com.motos.motos.service.impl;

import com.motos.motos.models.Motos;
import com.motos.motos.repository.MotosRepository;
import com.motos.motos.service.MotosService;
import com.motos.motos.client.UserService;
import com.motos.motos.models.dto.MotosFullDto;
import com.motos.motos.models.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class MotosServiceImpl implements MotosService {

    @Autowired
    private MotosRepository motosRepository;

    @Autowired
    private UserService userService;

    @Override
    public Page<Motos> getAllMotos(Pageable page, Specification<Motos> spec) {
        return motosRepository.findAll(spec,page);
    }

    @Override
    public Page<MotosFullDto> getFullMotos(Pageable page, Specification<Motos> spec) {
        Page<Motos> motosFound = getAllMotos(page, spec);
        List<MotosFullDto> motosDto = new ArrayList<>();
        List<UserDto> userDtos = userService.getUsersByIds(
                motosFound.getContent().stream().map(Motos::getUserId).toList()
        );

        motosFound.getContent().stream().forEach(moto->{
            var userFound = userDtos.stream().filter(u->u.getId().equals(moto.getUserId()))
                    .findFirst().orElse(null);

            motosDto.add(
                    MotosFullDto.builder()
                            .id(moto.getId())
                            .frente(moto.getFrente())
                            .motor(moto.getMotor())
                            .tanque(moto.getTanque())
                            .tras(moto.getTras())
                            .user(userFound)
                            .build()
            );
        });

        return new PageImpl<>(motosDto,
                              motosFound.getPageable(),
                              motosFound.getTotalElements());
    }

    @Override
    public List<Motos> saveMotos(List<Motos> motos) {
        List<UUID> motoIds = new ArrayList<>();
        motos.stream().forEach(moto -> {
            if( userService.checkIfUserExists(moto.getUserId()) ){
                motoIds.add(
                        motosRepository.saveAndFlush(moto).getId()
                );
            }
            else{
                log.error("User with id: {} does not exist!",
                        moto.getUserId());
            }
        });
        return motosRepository.findAllById(motoIds);
    }

    @Override
    public Motos updateMoto(Motos moto) {
        Optional<Motos> optionalMoto = motosRepository.findById(moto.getId());

        if(optionalMoto.isPresent()) {
            BeanUtils.copyProperties(moto,optionalMoto.get(),
                    "createdDate");
            return motosRepository.save(optionalMoto.get());
        }
        return null;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteMoto(UUID id) {
        Optional<Motos> optionalMoto = motosRepository.findById(id);
        if(optionalMoto.isPresent()){
            motosRepository.delete(optionalMoto.get());
        }
    }


}
