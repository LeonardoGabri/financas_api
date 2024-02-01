package com.financas.domain.service;

import com.financas.api.filter.FornecedorFilter;
import com.financas.domain.model.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FornecedorService {
    Page<Fornecedor> consultar(FornecedorFilter filter, Pageable page);
    Fornecedor salvar(Fornecedor request);
    Fornecedor procurarPorId(String id);
    void deletarPorId(String id);
}
