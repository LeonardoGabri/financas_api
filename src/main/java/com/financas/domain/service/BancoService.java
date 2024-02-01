package com.financas.domain.service;

import com.financas.api.filter.BancoFilter;
import com.financas.domain.model.Banco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BancoService {
    Page<Banco> consultar(BancoFilter filter, Pageable page);
    Banco salvar(Banco request);
    Banco procurarPorId(String id);
    void deletarPorId(String id);
}
