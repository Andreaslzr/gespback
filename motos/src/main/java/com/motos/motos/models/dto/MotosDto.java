package com.motos.motos.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.motos.motos.models.Motos;
import com.motos.motos.models.Parts;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class MotosDto implements Serializable {

    private UUID head;
    private UUID base;
    private UUID leftArm;
    private UUID rightArm;
    private UUID torso;
    private UUID userId;

    @JsonIgnore
    public Motos mapToMoto(){
        return Motos.builder()
                .frente(Parts.builder().id(this.head).build())
                .motor(Parts.builder().id(this.base).build())
                .tanque(Parts.builder().id(this.leftArm).build())
                .tras(Parts.builder().id(this.rightArm).build())
                .userId(this.userId)
                .build();
    }
}
