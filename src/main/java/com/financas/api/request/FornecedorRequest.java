package com.financas.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FornecedorRequest {
    @NotNull
    private String nome;
    private boolean ativo;
    private String descricao;

}
