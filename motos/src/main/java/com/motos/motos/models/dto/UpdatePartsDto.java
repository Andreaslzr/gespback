package com.motos.motos.models.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class UpdatePartsDto extends PartsDto
        implements Serializable {

    private UUID id;
}
