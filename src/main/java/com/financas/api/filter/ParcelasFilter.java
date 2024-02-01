package com.financas.api.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParcelasFilter {
    private String pesquisar;
    private int anoReferencia;
    private int mesReferencia;
    private String responsavelId;

}
