package com.financas.domain.service.impl;

import com.financas.api.filter.FornecedorFilter;
import com.financas.api.filter.ResponsavelFilter;
import com.financas.domain.model.Fornecedor;
import com.financas.domain.model.Responsavel;
import com.financas.domain.repository.FornecedorRepository;
import com.financas.domain.repository.ResponsavelRepository;
import com.financas.domain.repository.specifications.FornecedorSpecification;
import com.financas.domain.repository.specifications.ResponsavelSpecification;
import com.financas.domain.service.FornecedorService;
import com.financas.domain.service.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FornecedorServiceImpl implements FornecedorService {
    private final String MSG_NAO_ENCONTRADO = "Não encontrado registro com id = %s";
    private final String MSG_DUPLICADO = "Já cadastrado banco com nome = %s";

    private FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorServiceImpl(FornecedorRepository fornecedorRepository){
        this.fornecedorRepository = fornecedorRepository;
    }

    @Override
    public Page<Fornecedor> consultar(FornecedorFilter filtro, Pageable page) {
        return fornecedorRepository.findAll(new FornecedorSpecification(filtro), page);
    }

    @Override
    public Fornecedor salvar(Fornecedor request) {
        try{
            request.setDataAlteracao(LocalDateTime.now());
            request.setUsername("leonardogabriel8@hotmail.com");
            return fornecedorRepository.save(request);
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(String.format(MSG_DUPLICADO, request.getNome()));
        }
    }

    @Override
    public Fornecedor procurarPorId(String id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(MSG_NAO_ENCONTRADO, id)));
    }

    @Override
    public void deletarPorId(String id) {
        Fornecedor fornecedor = procurarPorId(id);
        fornecedorRepository.delete(fornecedor);
    }
}
