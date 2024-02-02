package com.financas.api.controller;

import com.financas.api.dto.BancoDto;
import com.financas.api.dto.ResponsavelDto;
import com.financas.api.filter.BancoFilter;
import com.financas.api.filter.ResponsavelFilter;
import com.financas.api.request.BancoRequest;
import com.financas.api.request.ResponsavelRequest;
import com.financas.domain.model.Banco;
import com.financas.domain.model.Responsavel;
import com.financas.domain.service.BancoService;
import com.financas.domain.service.ResponsavelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {

    private ResponsavelService responsavelService;
    private ModelMapper modelMapper;
    @Autowired
    public ResponsavelController(ResponsavelService responsavelService, ModelMapper modelMapper){
        this.responsavelService = responsavelService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/filtros")
    public Page<ResponsavelDto> listarResponsavelPaginado(ResponsavelFilter filtro, Pageable page){
        Page<Responsavel> listaResponsavelPaginado = responsavelService.consultar(filtro, page);
        List<ResponsavelDto> listaResponsavel = listaResponsavelPaginado.getContent().stream().map(responsavel -> modelMapper.map(responsavel, ResponsavelDto.class)).collect(Collectors.toList());
        return new PageImpl<ResponsavelDto>(listaResponsavel, page, listaResponsavelPaginado.getTotalElements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDto> procurarPorId(@PathVariable String id){
        Responsavel responsavel = responsavelService.procurarPorId(id);
        return ResponseEntity.ok(modelMapper.map(responsavel, ResponsavelDto.class));
    }

    @PostMapping
    public ResponseEntity<ResponsavelDto> inserir(@RequestBody @Valid ResponsavelRequest request){
        Responsavel responsavel = modelMapper.map(request, Responsavel.class);
        responsavel = responsavelService.salvar(responsavel);
        return ResponseEntity.ok(modelMapper.map(responsavel, ResponsavelDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluir(@PathVariable String id){
        responsavelService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsavelDto> alterar(@RequestBody @Valid ResponsavelRequest request, @PathVariable String id){
        Responsavel responsavelEditado  = modelMapper.map(request, Responsavel.class);
        responsavelEditado.setDataAlteracao(LocalDateTime.now());
        responsavelEditado.setId(id);
        responsavelService.salvar(responsavelEditado);
        return ResponseEntity.ok(modelMapper.map(responsavelEditado, ResponsavelDto.class));
    }
}
