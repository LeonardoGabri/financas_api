package com.financas.domain.service.impl;

import com.financas.api.filter.CompraFilter;
import com.financas.api.filter.ParcelasFilter;
import com.financas.api.filter.SubgrupoFilter;
import com.financas.domain.model.Compra;
import com.financas.domain.model.Parcelas;
import com.financas.domain.model.Subgrupo;
import com.financas.domain.repository.ParcelasRepository;
import com.financas.domain.repository.SubgrupoRepository;
import com.financas.domain.repository.specifications.CompraSpecification;
import com.financas.domain.repository.specifications.ParcelasSpecification;
import com.financas.domain.repository.specifications.SubgrupoSpecification;
import com.financas.domain.service.ParcelasService;
import com.financas.domain.service.SubgrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParcelasServiceImpl implements ParcelasService {
    private final String MSG_NAO_ENCONTRADO = "Não encontrado registro com id = %s";
    private final String MSG_DUPLICADO = "Já cadastrado banco com nome = %s";

    private ParcelasRepository parcelasRepository;

    @Autowired
    public ParcelasServiceImpl(ParcelasRepository parcelasRepository){
        this.parcelasRepository = parcelasRepository;
    }

    @Override
    public Page<Parcelas> consultar(ParcelasFilter filtro, Pageable page) {
        Page<Parcelas> parcelasPaginada = parcelasRepository.findAll(new ParcelasSpecification(filtro), page);
        return parcelasPaginada;
    }

    @Override
    public Double consultarValorParceladoTotal(ParcelasFilter filtro) {
        List<Parcelas> parcelas = parcelasRepository.findAll(new ParcelasSpecification(filtro));
        BigDecimal somaTotal = BigDecimal.ZERO;

        somaTotal = parcelas.stream()
                .map(item -> BigDecimal.valueOf(item.getValorParcelado()))
                .reduce(somaTotal, BigDecimal::add);

        return somaTotal.doubleValue();
    }

    @Override
    public Parcelas salvar(Parcelas request) {
        return parcelasRepository.save(request);
    }

    @Override
    public Parcelas procurarPorId(String id) {
        return parcelasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(MSG_NAO_ENCONTRADO, id)));
    }

    @Override
    public void deletarPorId(String id) {
        Parcelas parcela = procurarPorId(id);
        parcelasRepository.delete(parcela);
    }

    @Override
    public List<Parcelas> findByParcelasPorCompra(String idCompra) {
        return parcelasRepository.findByParcelasPorCompra(idCompra)
                .orElseThrow(() -> new RuntimeException(String.format(MSG_NAO_ENCONTRADO, idCompra)));
    }
}
