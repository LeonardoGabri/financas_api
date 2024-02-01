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
public class BancoDto {
    private String id;
    private BancoEnum nome;
    private String nomeFormatado;
    private String responsavel;
    private String responsavelNome;
    private boolean ativo;
    private String descricao;
}
