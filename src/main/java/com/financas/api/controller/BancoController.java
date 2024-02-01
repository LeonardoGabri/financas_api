package com.financas.api.controller;

import com.financas.api.dto.BancoDto;
import com.financas.api.filter.BancoFilter;
import com.financas.api.request.BancoRequest;
import com.financas.domain.enums.BancoEnum;
import com.financas.domain.model.Banco;
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
@CrossOrigin(origins = "${cors.origin:http://localhost:4200}")
@RestController
@RequestMapping("/banco")
public class BancoController {

    private BancoService bancoService;

    private ResponsavelService responsavelService;
    private ModelMapper modelMapper;
    @Autowired
    public BancoController(BancoService bancoService, ResponsavelService responsavelService, ModelMapper modelMapper){
        this.bancoService = bancoService;
        this.responsavelService = responsavelService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/filtros")
    public Page<BancoDto> listarBancosPaginado(BancoFilter filtro, Pageable page){
        Page<Banco> listaBancosPaginado = bancoService.consultar(filtro, page);
        List<BancoDto> listaBanco = listaBancosPaginado.getContent().stream().map(banco -> {
            BancoDto bancoDto = modelMapper.map(banco, BancoDto.class);
            bancoDto.setNomeFormatado(bancoDto.getNome().getChave());
            return bancoDto;
        }).collect(Collectors.toList());
        return new PageImpl<BancoDto>(listaBanco, page, listaBancosPaginado.getTotalElements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BancoDto> procurarPorId(@PathVariable String id){
        Banco banco = bancoService.procurarPorId(id);
        return ResponseEntity.ok(modelMapper.map(banco, BancoDto.class));
    }

    @PostMapping
    public ResponseEntity<BancoDto> inserir(@RequestBody @Valid BancoRequest request){
        Banco banco = modelMapper.map(request, Banco.class);
        banco = bancoService.salvar(banco);
        return ResponseEntity.ok(modelMapper.map(banco, BancoDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluir(@PathVariable String id){
        bancoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BancoDto> alterar(@RequestBody @Valid BancoRequest request, @PathVariable String id){
        Banco bancoEditado  = modelMapper.map(request, Banco.class);
        bancoEditado.setDataAlteracao(LocalDateTime.now());
        bancoEditado.setId(id);
        bancoService.salvar(bancoEditado);
        return ResponseEntity.ok(modelMapper.map(bancoEditado, BancoDto.class));
    }
}
