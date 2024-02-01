package com.financas.domain.service.impl;

import com.financas.api.filter.GrupoFilter;
import com.financas.api.filter.ResponsavelFilter;
import com.financas.domain.model.Grupo;
import com.financas.domain.model.Responsavel;
import com.financas.domain.repository.GrupoRepository;
import com.financas.domain.repository.ResponsavelRepository;
import com.financas.domain.repository.specifications.GrupoSpecification;
import com.financas.domain.repository.specifications.ResponsavelSpecification;
import com.financas.domain.service.GrupoService;
import com.financas.domain.service.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GrupoServiceImpl implements GrupoService {
    private final String MSG_NAO_ENCONTRADO = "Não encontrado registro com id = %s";
    private final String MSG_DUPLICADO = "Já cadastrado banco com nome = %s";

    private GrupoRepository grupoRepository;

    @Autowired
    public GrupoServiceImpl(GrupoRepository grupoRepository){
        this.grupoRepository = grupoRepository;
    }

    @Override
    public Page<Grupo> consultar(GrupoFilter filtro, Pageable page) {
        return grupoRepository.findAll(new GrupoSpecification(filtro), page);
    }

    @Override
    public Grupo salvar(Grupo request) {
        try{
            request.setDataAlteracao(LocalDateTime.now());
            request.setUsername("leonardogabriel8@hotmail.com");
            return grupoRepository.save(request);
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(String.format(MSG_DUPLICADO, request.getNome()));
        }
    }

    @Override
    public Grupo procurarPorId(String id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(MSG_NAO_ENCONTRADO, id)));
    }

    @Override
    public void deletarPorId(String id) {
        Grupo grupo = procurarPorId(id);
        grupoRepository.delete(grupo);
    }
}
