package com.financas.api.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraFilter {
    private String bancoId;
    private String fornecedorId;
    private String subgrupoId;
    private String parcelas;
    private String parcelasAtual;
    private String pesquisar;
    private String dataInicio;
    private String dataFim;
}

