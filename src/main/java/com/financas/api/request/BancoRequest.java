package com.financas.api.request;

import com.financas.domain.enums.BancoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BancoRequest {
    @NotNull
    private BancoEnum nome;
    private String responsavel;
    private String responsavelNome;

    private boolean ativo;
    private String descricao;
}
