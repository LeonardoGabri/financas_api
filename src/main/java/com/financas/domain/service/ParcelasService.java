package com.financas.domain.service;

import com.financas.api.filter.ParcelasFilter;
import com.financas.api.filter.ResponsavelFilter;
import com.financas.domain.model.Parcelas;
import com.financas.domain.model.Responsavel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ParcelasService {
    Page<Parcelas> consultar(ParcelasFilter filter, Pageable page);
    Parcelas salvar(Parcelas request);
    Parcelas procurarPorId(String id);
    void deletarPorId(String id);
    List<Parcelas> findByParcelasPorCompra(String idCompra);
    Double consultarValorParceladoTotal(ParcelasFilter filter);

}
