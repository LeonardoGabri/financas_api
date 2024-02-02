package com.financas.api.controller;

import com.financas.api.dto.CompraDto;
import com.financas.api.dto.GrupoDto;
import com.financas.api.dto.ParcelasDto;
import com.financas.api.dto.RetornoNomeEntidadesDto;
import com.financas.api.filter.GrupoFilter;
import com.financas.api.filter.ParcelasFilter;
import com.financas.api.request.GrupoRequest;
import com.financas.api.request.ParcelasRequest;
import com.financas.domain.model.Compra;
import com.financas.domain.model.Grupo;
import com.financas.domain.model.Parcelas;
import com.financas.domain.service.CompraService;
import com.financas.domain.service.GrupoService;
import com.financas.domain.service.ParcelasService;
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
@RequestMapping("/parcelas")
public class ParcelasController {

    private ParcelasService parcelasService;
    private CompraService compraService;
    private ResponsavelService responsavelService;
    private ModelMapper modelMapper;
    @Autowired
    public ParcelasController(ParcelasService parcelasService, ResponsavelService responsavelService, CompraService compraService, ModelMapper modelMapper){
        this.parcelasService = parcelasService;
        this.responsavelService = responsavelService;
        this.compraService = compraService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/filtros")
    public Page<ParcelasDto> listarGrupoPaginado(ParcelasFilter filtro, Pageable page){
        Page<Parcelas> listaParcelasPaginado = parcelasService.consultar(filtro, page);
        List<ParcelasDto> listaParcelas = listaParcelasPaginado.getContent().stream().map(parcela -> converter(parcela)).collect(Collectors.toList());
        return new PageImpl<ParcelasDto>(listaParcelas, page, listaParcelasPaginado.getTotalElements());
    }

    @GetMapping("/valorTotal/filtros")
    public Double valorTotalFiltro(ParcelasFilter filtro){
        return parcelasService.consultarValorParceladoTotal(filtro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParcelasDto> procurarPorId(@PathVariable String id){
        Parcelas parcelas = parcelasService.procurarPorId(id);
        return ResponseEntity.ok(modelMapper.map(parcelas, ParcelasDto.class));
    }

    @PostMapping
    public ResponseEntity<ParcelasDto> inserir(@RequestBody @Valid ParcelasRequest request){
        Parcelas parcelas = modelMapper.map(request, Parcelas.class);
        parcelas = parcelasService.salvar(parcelas);
        return ResponseEntity.ok(modelMapper.map(parcelas, ParcelasDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluir(@PathVariable String id){
        parcelasService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParcelasDto> alterar(@RequestBody @Valid ParcelasRequest request, @PathVariable String id){
        Parcelas parcelaEditada = modelMapper.map(request, Parcelas.class);
        parcelaEditada.setId(id);
        parcelasService.salvar(parcelaEditada);
        return ResponseEntity.ok(modelMapper.map(parcelaEditada, ParcelasDto.class));
    }

    private ParcelasDto converter(Parcelas parcela) {
        ParcelasDto parcelaDto = modelMapper.map(parcela, ParcelasDto.class);

        parcelaDto.setQuantidadeParcelas(compraService.procurarPorId(parcela.getCompraId()).getParcelas());
        return parcelaDto;
    }
}
