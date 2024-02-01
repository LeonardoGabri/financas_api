package com.financas.api.dto;

import com.financas.domain.enums.BancoEnum;
import com.financas.domain.model.Parcelas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetornoNomeEntidadesDto {
    private String bancoNome;
    private String fornecedorNome;
    private String responsavelNome;
    private String subgrupoNome;
    private String grupoNome;
    private List<Parcelas> parcelas;
}
