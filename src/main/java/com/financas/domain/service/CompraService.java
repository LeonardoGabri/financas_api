package com.financas.domain.service;

import com.financas.api.dto.RetornoNomeEntidadesDto;
import com.financas.api.filter.CompraFilter;
import com.financas.api.filter.SubgrupoFilter;
import com.financas.api.request.CompraRequest;
import com.financas.domain.model.Compra;
import com.financas.domain.model.Subgrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompraService {
    Page<Compra> consultar(CompraFilter filter, Pageable page);
    Compra salvar(CompraRequest request) throws Exception;
    Compra procurarPorId(String id);
    void deletarPorId(String id);
    Compra atualizar(CompraRequest request, String id);
}
