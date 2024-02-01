package com.financas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Parcelas{
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name="UUIDGenerator", strategy = "uuid2")
    private String id;
    @Column(name = "mes_referencia")
    private int mesReferencia;
    @Column(name = "ano_referencia")
    private int anoReferencia;
    @Column(name = "parcela_atual")
    private int parcelaAtual;
    @Column(name = "compra_id")
    private String compraId;
    @Column(name = "valor_parcelado")
    private Double valorParcelado;
    @Column(name = "responsavel")
    private String responsavel;
    @Column(name = "responsavel_nome")
    private String responsavelNome;

}
