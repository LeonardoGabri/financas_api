package com.financas.api.filter;

import com.financas.domain.model.Banco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubgrupoFilter {
    private String nome;
    private String pesquisar;
    private String grupoId;

}
