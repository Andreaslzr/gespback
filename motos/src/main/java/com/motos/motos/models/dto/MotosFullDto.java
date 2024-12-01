package com.motos.motos.models.dto;

import com.motos.motos.models.Parts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MotosFullDto implements Serializable {

    private UUID id;
    private Parts frente;
    private Parts motor;
    private Parts tanque;
    private Parts tras;
    private UserDto user;
    private LocalDateTime createdDate;
    private BigDecimal totalCost;

}
