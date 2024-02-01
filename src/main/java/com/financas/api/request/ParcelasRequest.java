package com.financas.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParcelasRequest {
    private String responsavelId;

    private int mesReferencia;

    private int anoReferencia;

    private int parcelaAtual;

    private Double valorParcelado;
}
