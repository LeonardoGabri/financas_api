package com.financas.api.controller;

import com.financas.api.dto.GrupoDto;
import com.financas.api.dto.SubgrupoDto;
import com.financas.api.filter.GrupoFilter;
import com.financas.api.filter.SubgrupoFilter;
import com.financas.api.request.GrupoRequest;
import com.financas.api.request.SubgrupoRequest;
import com.financas.domain.model.Grupo;
import com.financas.domain.model.Subgrupo;
import com.financas.domain.service.GrupoService;
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
@RequestMapping("/subgrupo")
public class SubgrupoController {

    private SubgrupoService subgrupoService;
    private ModelMapper modelMapper;

    private GrupoService grupoService;
    @Autowired
    public SubgrupoController(SubgrupoService subgrupoService, GrupoService grupoService, ModelMapper modelMapper){
        this.subgrupoService = subgrupoService;
        this.modelMapper = modelMapper;
        this.grupoService = grupoService;
    }
    @GetMapping("/filtros")
    public Page<SubgrupoDto> listarSubgrupoPaginado(SubgrupoFilter filtro, Pageable page){
        Page<Subgrupo> listaSubgrupoPaginado = subgrupoService.consultar(filtro, page);
        List<SubgrupoDto> listaSubgrupo = listaSubgrupoPaginado.getContent().stream().map(subgrupo -> {
            SubgrupoDto subgrupoDto = modelMapper.map(subgrupo, SubgrupoDto.class);
            subgrupoDto.setGrupoNome(grupoService.procurarPorId(subgrupoDto.getGrupoId()).getNome());
            return subgrupoDto;
        }).collect(Collectors.toList());
        return new PageImpl<SubgrupoDto>(listaSubgrupo, page, listaSubgrupoPaginado.getTotalElements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubgrupoDto> procurarPorId(@PathVariable String id){
        Subgrupo subgrupo = subgrupoService.procurarPorId(id);
        return ResponseEntity.ok(modelMapper.map(subgrupo, SubgrupoDto.class));
    }

    @PostMapping
    public ResponseEntity<SubgrupoDto> inserir(@RequestBody @Valid SubgrupoRequest request){
        Subgrupo subgrupo = modelMapper.map(request, Subgrupo.class);
        subgrupo = subgrupoService.salvar(subgrupo);
        return ResponseEntity.ok(modelMapper.map(subgrupo, SubgrupoDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluir(@PathVariable String id){
        subgrupoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubgrupoDto> alterar(@RequestBody @Valid SubgrupoRequest request, @PathVariable String id){
        Subgrupo subgrupoEditado  = modelMapper.map(request, Subgrupo.class);
        subgrupoEditado.setDataAlteracao(LocalDateTime.now());
        subgrupoEditado.setId(id);
        subgrupoService.salvar(subgrupoEditado);
        return ResponseEntity.ok(modelMapper.map(subgrupoEditado, SubgrupoDto.class));
    }
}
