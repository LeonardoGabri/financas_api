package com.financas.api.controller;

import com.financas.api.dto.CompraDto;
import com.financas.api.dto.RetornoNomeEntidadesDto;
import com.financas.api.dto.SubgrupoDto;
import com.financas.api.filter.CompraFilter;
import com.financas.api.filter.SubgrupoFilter;
import com.financas.api.request.CompraRequest;
import com.financas.api.request.SubgrupoRequest;
import com.financas.domain.model.Compra;
import com.financas.domain.model.ResponsavelCompra;
import com.financas.domain.model.Subgrupo;
import com.financas.domain.service.CompraService;
import com.financas.domain.service.SubgrupoService;
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
@RequestMapping("/compra")
public class CompraController {

    private CompraService compraService;
    private ModelMapper modelMapper;
    @Autowired
    public CompraController(CompraService compraService, ModelMapper modelMapper){
        this.compraService = compraService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/filtros")
    public Page<CompraDto> listarCompraPaginado(CompraFilter filtro, Pageable page){
        Page<Compra> listaCompraPaginado = compraService.consultar(filtro, page);
        List<CompraDto> listaCompra = listaCompraPaginado.getContent().stream().map(compra -> modelMapper.map(compra, CompraDto.class)).collect(Collectors.toList());
        return new PageImpl<CompraDto>(listaCompra, page, listaCompraPaginado.getTotalElements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraDto> procurarPorId(@PathVariable String id){
        Compra compra = compraService.procurarPorId(id);
        return ResponseEntity.ok(modelMapper.map(compra, CompraDto.class));
    }

    @PostMapping
    public ResponseEntity<CompraDto> inserir(@RequestBody @Valid CompraRequest request) throws Exception {
        Compra compra = compraService.salvar(request);
        return ResponseEntity.ok(modelMapper.map(compra, CompraDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluir(@PathVariable String id){
        compraService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraDto> alterar(@RequestBody @Valid CompraRequest request, @PathVariable String id){
        Compra compraEditada = compraService.atualizar(request, id);
        return ResponseEntity.ok(modelMapper.map(compraEditada, CompraDto.class));
    }
}
