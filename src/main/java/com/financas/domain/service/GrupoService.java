package com.financas.domain.service;

import com.financas.api.filter.GrupoFilter;
import com.financas.domain.model.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GrupoService {
    Page<Grupo> consultar(GrupoFilter filter, Pageable page);
    Grupo salvar(Grupo request);
    Grupo procurarPorId(String id);
    void deletarPorId(String id);
}
