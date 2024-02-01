package com.financas.domain.model;

import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.FilterDef;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private LocalDateTime dataAlteracao;
    private String username;
}
