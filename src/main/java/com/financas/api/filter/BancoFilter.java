package com.financas.api.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BancoFilter {
    private String nome;
    private boolean ativo;
    private String pesquisar;
}
