package com.financas.api.dto;

import com.financas.domain.model.Grupo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubgrupoDto {
    private String id;
    private String nome;
    private String grupoId;
    private String grupoNome;
    private String descricao;
}
