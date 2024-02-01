package com.financas.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FornecedorDto {
    private String id;
    private String nome;
    private String descricao;
    private boolean ativo;
}
