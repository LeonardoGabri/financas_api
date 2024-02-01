package com.financas.domain.service.impl;

import com.financas.api.filter.GrupoFilter;
import com.financas.api.filter.SubgrupoFilter;
import com.financas.domain.model.Grupo;
import com.financas.domain.model.Subgrupo;
import com.financas.domain.repository.GrupoRepository;
import com.financas.domain.repository.SubgrupoRepository;
import com.financas.domain.repository.specifications.GrupoSpecification;
import com.financas.domain.repository.specifications.SubgrupoSpecification;
import com.financas.domain.service.GrupoService;
import com.financas.domain.service.SubgrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubgrupoServiceImpl implements SubgrupoService {
    private final String MSG_NAO_ENCONTRADO = "Não encontrado registro com id = %s";
    private final String MSG_DUPLICADO = "Já cadastrado banco com nome = %s";

    private SubgrupoRepository subgrupoRepository;

    @Autowired
    public SubgrupoServiceImpl(SubgrupoRepository subgrupoRepository){
        this.subgrupoRepository = subgrupoRepository;
    }

    @Override
    public Page<Subgrupo> consultar(SubgrupoFilter filtro, Pageable page) {
        return subgrupoRepository.findAll(new SubgrupoSpecification(filtro), page);
    }

    @Override
    public Subgrupo salvar(Subgrupo request) {
        try{
            request.setDataAlteracao(LocalDateTime.now());
            request.setUsername("leonardogabriel8@hotmail.com");
            return subgrupoRepository.save(request);
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(String.format(MSG_DUPLICADO, request.getNome()));
        }
    }

    @Override
    public Subgrupo procurarPorId(String id) {
        return subgrupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(MSG_NAO_ENCONTRADO, id)));
    }

    @Override
    public void deletarPorId(String id) {
        Subgrupo subgrupo = procurarPorId(id);
        subgrupoRepository.delete(subgrupo);
    }
}
