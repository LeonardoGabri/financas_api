package com.financas.api.dto;

import com.financas.domain.enums.TipoCompraEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraDto {
    private String id;
    private String banco;
    private String bancoNome;
    private String fornecedor;
    private String fornecedorNome;
    private String descricao;
    private LocalDate dataCompra;
    private String subgrupo;
    private String subgrupoNome;
    private String grupoNome;
    private int parcelas;
    private Double valor;
    private Integer mesInicioCobranca;
    private Integer anoInicioCobranca;
    List<ResponsavelCompraDto> responsaveis;
    private Double aliquotaImposta;
    private TipoCompraEnum tipoCompra;
}
