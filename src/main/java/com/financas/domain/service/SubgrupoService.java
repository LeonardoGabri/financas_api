package com.financas.domain.service;

import com.financas.api.filter.GrupoFilter;
import com.financas.api.filter.SubgrupoFilter;
import com.financas.domain.model.Grupo;
import com.financas.domain.model.Subgrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubgrupoService {
    Page<Subgrupo> consultar(SubgrupoFilter filter, Pageable page);
    Subgrupo salvar(Subgrupo request);
    Subgrupo procurarPorId(String id);
    void deletarPorId(String id);
}
