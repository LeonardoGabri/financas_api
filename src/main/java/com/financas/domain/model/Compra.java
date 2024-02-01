package com.financas.domain.model;

import com.financas.domain.enums.TipoCompraEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@DynamicUpdate
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Compra extends BaseEntity{
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name="UUIDGenerator", strategy = "uuid2")
    private String id;
    @Column(name = "banco")
    private String banco;
    private String descricao;
    @Column(name = "banco_nome")
    private String bancoNome;
    @Column(name = "fornecedor")
    private String fornecedor;
    @Column(name = "fornecedor_nome")
    private String fornecedorNome;
    @Column(name = "parcelas")
    private int parcelas;
    @Column(name = "data_compra")
    private LocalDate dataCompra;
    @Column(name = "subgrupo")
    private String subgrupo;
    @Column(name = "subgrupo_nome")
    private String subgrupoNome;
    private Double valor;
    @Column(name = "mes_inicio_cobranca")
    private Integer mesInicioCobranca;
    @Column(name = "ano_inicio_cobranca")
    private Integer anoInicioCobranca;
    @ElementCollection
    @CollectionTable(name = "responsavel_compra", joinColumns = @JoinColumn(name = "compra_id"))
    private List<ResponsavelCompra> responsaveis;
    @Column(name = "aliquota_imposta")
    private Double aliquotaImposta;
    @Column(name = "tipo_compra")
    private TipoCompraEnum tipoCompra;
}
