package com.financas.api.dto;

import com.financas.domain.model.Parcelas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsavelCompraDto {
    private String id;
    private String responsavel;
    private Double percentual;
}
