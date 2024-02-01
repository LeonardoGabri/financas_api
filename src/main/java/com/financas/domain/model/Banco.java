package com.financas.domain.model;

import com.financas.domain.enums.BancoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
@Entity
@DynamicUpdate
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Banco extends BaseEntity{
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name="UUIDGenerator", strategy = "uuid2")
    private String id;
    private BancoEnum nome;
    @Column(name = "responsavel")
    private String responsavel;
    @Column(name = "responsavel_nome")
    private String responsavelNome;
    private Boolean ativo;
    private String descricao;
}
