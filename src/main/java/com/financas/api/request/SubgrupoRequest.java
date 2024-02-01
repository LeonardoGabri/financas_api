package com.financas.api.request;

import com.financas.domain.model.Banco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubgrupoRequest {
    @NotNull
    private String nome;

    private String descricao;

    private String grupoId;
}
