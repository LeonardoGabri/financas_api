package com.financas.api.controller;

import com.financas.api.dto.GrupoDto;
import com.financas.api.dto.ResponsavelDto;
import com.financas.api.filter.GrupoFilter;
import com.financas.api.filter.ResponsavelFilter;
import com.financas.api.request.GrupoRequest;
import com.financas.api.request.ResponsavelRequest;
import com.financas.domain.model.Grupo;
import com.financas.domain.model.Responsavel;
import com.financas.domain.service.GrupoService;
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
@RequestMapping("/grupo")
public class GrupoController {

    private GrupoService grupoService;
    private ModelMapper modelMapper;
    @Autowired
    public GrupoController(GrupoService grupoService, ModelMapper modelMapper){
        this.grupoService = grupoService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/filtros")
    public Page<GrupoDto> listarGrupoPaginado(GrupoFilter filtro, Pageable page){
        Page<Grupo> listaGrupoPaginado = grupoService.consultar(filtro, page);
        List<GrupoDto> listaGrupo = listaGrupoPaginado.getContent().stream().map(grupo -> modelMapper.map(grupo, GrupoDto.class)).collect(Collectors.toList());
        return new PageImpl<GrupoDto>(listaGrupo, page, listaGrupoPaginado.getTotalElements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoDto> procurarPorId(@PathVariable String id){
        Grupo grupo = grupoService.procurarPorId(id);
        return ResponseEntity.ok(modelMapper.map(grupo, GrupoDto.class));
    }

    @PostMapping
    public ResponseEntity<GrupoDto> inserir(@RequestBody @Valid GrupoRequest request){
        Grupo grupo = modelMapper.map(request, Grupo.class);
        grupo = grupoService.salvar(grupo);
        return ResponseEntity.ok(modelMapper.map(grupo, GrupoDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluir(@PathVariable String id){
        grupoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoDto> alterar(@RequestBody @Valid GrupoRequest request, @PathVariable String id){
        Grupo grupoEditado  = modelMapper.map(request, Grupo.class);
        grupoEditado.setDataAlteracao(LocalDateTime.now());
        grupoEditado.setId(id);
        grupoService.salvar(grupoEditado);
        return ResponseEntity.ok(modelMapper.map(grupoEditado, GrupoDto.class));
    }
}
