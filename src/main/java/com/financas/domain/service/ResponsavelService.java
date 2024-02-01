package com.financas.domain.service;

import com.financas.api.filter.BancoFilter;
import com.financas.api.filter.ResponsavelFilter;
import com.financas.domain.model.Banco;
import com.financas.domain.model.Responsavel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResponsavelService {
    Page<Responsavel> consultar(ResponsavelFilter filter, Pageable page);
    Responsavel salvar(Responsavel request);
    Responsavel procurarPorId(String id);
    void deletarPorId(String id);
}
