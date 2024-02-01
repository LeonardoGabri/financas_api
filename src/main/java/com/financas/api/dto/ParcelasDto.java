package com.financas.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParcelasDto {
    private String id;
    private String responsavel;
    private String compraId;
    private String responsavelNome;
    private int mesReferencia;
    private int anoReferencia;
    private int parcelaAtual;
    private int quantidadeParcelas;
    private Double valorParcelado;
}
