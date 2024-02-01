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

@Entity
@DynamicUpdate
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Subgrupo extends BaseEntity{
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name="UUIDGenerator", strategy = "uuid2")
    private String id;
    private String nome;
    private String descricao;
    @Column(name = "grupo_id")
    private String grupoId;



}
