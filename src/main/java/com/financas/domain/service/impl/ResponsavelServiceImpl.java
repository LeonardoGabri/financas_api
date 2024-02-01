package com.financas.domain.service.impl;

import com.financas.api.filter.BancoFilter;
import com.financas.api.filter.ResponsavelFilter;
import com.financas.domain.model.Responsavel;
import com.financas.domain.repository.ResponsavelRepository;
import com.financas.domain.repository.specifications.BancoSpecification;
import com.financas.domain.repository.specifications.ResponsavelSpecification;
import com.financas.domain.service.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResponsavelServiceImpl implements ResponsavelService {
    private final String MSG_NAO_ENCONTRADO = "Não encontrado registro com id = %s";
    private final String MSG_DUPLICADO = "Já cadastrado banco com nome = %s";

    private ResponsavelRepository responsavelRepository;

    @Autowired
    public ResponsavelServiceImpl(ResponsavelRepository responsavelRepository){
        this.responsavelRepository = responsavelRepository;
    }

    @Override
    public Page<Responsavel> consultar(ResponsavelFilter filtro, Pageable page) {
        return responsavelRepository.findAll(new ResponsavelSpecification(filtro), page);
    }

    @Override
    public Responsavel salvar(Responsavel request) {
        try{
            request.setDataAlteracao(LocalDateTime.now());
            request.setUsername("leonardogabriel8@hotmail.com");
            return responsavelRepository.save(request);
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(String.format(MSG_DUPLICADO, request.getNome()));
        }
    }

    @Override
    public Responsavel procurarPorId(String id) {
        return responsavelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(MSG_NAO_ENCONTRADO, id)));
    }

    @Override
    public void deletarPorId(String id) {
        Responsavel banco = procurarPorId(id);
        responsavelRepository.delete(banco);
    }
}
