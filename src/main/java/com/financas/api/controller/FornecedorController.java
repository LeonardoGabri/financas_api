package com.financas.api.controller;

import com.financas.api.dto.FornecedorDto;
import com.financas.api.dto.ResponsavelDto;
import com.financas.api.filter.FornecedorFilter;
import com.financas.api.filter.ResponsavelFilter;
import com.financas.api.request.FornecedorRequest;
import com.financas.api.request.ResponsavelRequest;
import com.financas.domain.model.Fornecedor;
import com.financas.domain.model.Responsavel;
import com.financas.domain.service.FornecedorService;
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
@CrossOrigin(origins = "${cors.origin:http://localhost:4200}")
@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    private FornecedorService fornecedorService;
    private ModelMapper modelMapper;
    @Autowired
    public FornecedorController(FornecedorService fornecedorService, ModelMapper modelMapper){
        this.fornecedorService = fornecedorService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/filtros")
    public Page<FornecedorDto> listarFornecedoresPaginado(FornecedorFilter filtro, Pageable page){
        Page<Fornecedor> listaFornecedorPaginado = fornecedorService.consultar(filtro, page);
        List<FornecedorDto> listaFornecedor = listaFornecedorPaginado.getContent().stream().map(fornecedor -> modelMapper.map(fornecedor, FornecedorDto.class)).collect(Collectors.toList());
        return new PageImpl<FornecedorDto>(listaFornecedor, page, listaFornecedorPaginado.getTotalElements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDto> procurarPorId(@PathVariable String id){
        Fornecedor fornecedor = fornecedorService.procurarPorId(id);
        return ResponseEntity.ok(modelMapper.map(fornecedor, FornecedorDto.class));
    }

    @PostMapping
    public ResponseEntity<FornecedorDto> inserir(@RequestBody @Valid FornecedorRequest request){
        Fornecedor fornecedor = modelMapper.map(request, Fornecedor.class);
        fornecedor = fornecedorService.salvar(fornecedor);
        return ResponseEntity.ok(modelMapper.map(fornecedor, FornecedorDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluir(@PathVariable String id){
        fornecedorService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDto> alterar(@RequestBody @Valid FornecedorRequest request, @PathVariable String id){
        Fornecedor fornecedorEditado  = modelMapper.map(request, Fornecedor.class);
        fornecedorEditado.setDataAlteracao(LocalDateTime.now());
        fornecedorEditado.setId(id);
        fornecedorService.salvar(fornecedorEditado);
        return ResponseEntity.ok(modelMapper.map(fornecedorEditado, FornecedorDto.class));
    }
}
