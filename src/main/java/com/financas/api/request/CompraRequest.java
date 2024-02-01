package com.financas.api.request;

import com.financas.api.dto.ResponsavelCompraDto;
import com.financas.domain.enums.TipoCompraEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraRequest {
    @NotNull
    private String banco;
    private String bancoNome;
    @NotNull
    private String fornecedor;
    private String fornecedorNome;
    private String descricao;
    private LocalDate dataCompra;
    @NotNull
    private String subgrupo;
    private String subgrupoNome;
    @NotNull
    private int parcelas;
    @NotNull
    private Double valor;
    @NotNull
    private int mesInicioCobranca;
    @NotNull
    private int anoInicioCobranca;
    @NotNull
    List<ResponsavelCompraDto> responsaveis;
    private Double aliquotaImposta;
    @NotNull
    private TipoCompraEnum tipoCompra;
}
