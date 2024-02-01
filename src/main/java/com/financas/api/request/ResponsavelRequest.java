package com.financas.api.request;

import com.financas.domain.enums.BancoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsavelRequest {
    @NotNull
    private String nome;
    private String descricao;
    private boolean ativo;
}
