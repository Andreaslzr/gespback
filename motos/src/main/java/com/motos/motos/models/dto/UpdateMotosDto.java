package com.motos.motos.models.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class UpdateMotosDto extends MotosDto
        implements Serializable {
    private UUID id;
}
