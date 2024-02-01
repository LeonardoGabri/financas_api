package com.financas.domain.service.impl;

import com.financas.api.filter.BancoFilter;
import com.financas.domain.model.Banco;
import com.financas.domain.repository.BancoRepository;
import com.financas.domain.repository.specifications.BancoSpecification;
import com.financas.domain.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BancoServiceImpl implements BancoService {
    private final String MSG_NAO_ENCONTRADO = "Não encontrado registro com id = %s";
    private final String MSG_DUPLICADO = "Já cadastrado banco com nome = %s";

    private BancoRepository bancoRepository;

    @Autowired
    public BancoServiceImpl(BancoRepository bancoRepository){
        this.bancoRepository = bancoRepository;
    }

    @Override
    public Page<Banco> consultar(BancoFilter filtro, Pageable page) {
        return bancoRepository.findAll(new BancoSpecification(filtro), page);
    }

    @Override
    public Banco salvar(Banco request) {
        try{
            request.setDataAlteracao(LocalDateTime.now());
            request.setUsername("leonardogabriel8@hotmail.com");
            return bancoRepository.save(request);
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException(String.format(MSG_DUPLICADO, request.getNome()));
        }
    }

    @Override
    public Banco procurarPorId(String id) {
        return bancoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(MSG_NAO_ENCONTRADO, id)));
    }

    @Override
    public void deletarPorId(String id) {
        Banco banco = procurarPorId(id);
        bancoRepository.delete(banco);
    }
}
