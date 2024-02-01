package com.financas.api.dto;

import com.financas.domain.enums.BancoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsavelDto {
    private String id;
    private String nome;
    private String descricao;
    private boolean ativo;
}
