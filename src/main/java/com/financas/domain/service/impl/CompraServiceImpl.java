package com.financas.domain.service.impl;

import com.financas.api.filter.CompraFilter;
import com.financas.api.request.CompraRequest;
import com.financas.domain.model.Compra;
import com.financas.domain.model.Parcelas;
import com.financas.domain.model.ResponsavelCompra;
import com.financas.domain.repository.CompraRepository;
import com.financas.domain.repository.specifications.CompraSpecification;
import com.financas.domain.service.CompraService;
import com.financas.domain.service.ParcelasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraServiceImpl implements CompraService {
    private final String MSG_NAO_ENCONTRADO = "Não encontrado registro com id = %s";
    private final String MSG_DUPLICADO = "Já cadastrado banco com nome = %s";

    private ParcelasService parcelasService;
    private CompraRepository compraRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CompraServiceImpl(CompraRepository compraRepository,
                             ParcelasService parcelasService,
                             ModelMapper modelMapper
    ) {
        this.compraRepository = compraRepository;
        this.parcelasService = parcelasService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<Compra> consultar(CompraFilter filtro, Pageable page) {
        Page<Compra> comprasPaginada = compraRepository.findAll(new CompraSpecification(filtro), page);
        return comprasPaginada;
    }

    @Override
    public Compra salvar(CompraRequest request) throws Exception {
        try {
            List<ResponsavelCompra> responsaveis = request.getResponsaveis()
                    .stream()
                    .map(item -> modelMapper.map(item, ResponsavelCompra.class))
                    .collect(Collectors.toList());

            double somaPercentuais = responsaveis.stream()
                    .mapToDouble(ResponsavelCompra::getPercentual)
                    .sum();

            if (somaPercentuais != 100) {
                throw new RuntimeException(String.format("Divisão do percentual não está correta e diferente de 100"));
            }

            Compra compra = Compra.builder()
                    .responsaveis(responsaveis)
                    .banco(request.getBanco())
                    .bancoNome(request.getBancoNome())
                    .fornecedor(request.getFornecedor())
                    .fornecedorNome(request.getFornecedorNome())
                    .descricao(request.getDescricao())
                    .dataCompra(request.getDataCompra())
                    .subgrupo(request.getSubgrupo())
                    .subgrupoNome(request.getSubgrupoNome())
                    .parcelas(request.getParcelas())
                    .valor(request.getValor())
                    .mesInicioCobranca(request.getMesInicioCobranca())
                    .anoInicioCobranca(request.getAnoInicioCobranca())
                    .aliquotaImposta(request.getAliquotaImposta())
                    .tipoCompra(request.getTipoCompra())
                    .build();

            compra.setDataAlteracao(LocalDateTime.now());
            compra.setUsername("leonardogabriel8@hotmail.com");

            Compra compraRegistrada = compraRepository.save(compra);

            confeccionarParcelas(compraRegistrada);

            return compraRegistrada;
        } catch (Exception e) {
            throw new Exception(String.format("Erro ao salvar"), e);
        }
    }


    private void confeccionarParcelas(Compra compraRegistrada) {
        compraRegistrada.getResponsaveis().stream().forEach(item -> {
            BigDecimal percentualComoBigDecimal = BigDecimal.valueOf(item.getPercentual()).divide(BigDecimal.valueOf(100));
            BigDecimal valorTotalPorResponsavel = BigDecimal.valueOf(compraRegistrada.getAliquotaImposta() != 0.0 ? compraRegistrada.getValor() * compraRegistrada.getAliquotaImposta() : compraRegistrada.getValor())
                    .multiply(percentualComoBigDecimal)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal valorParceladoPorResponsavel = valorTotalPorResponsavel
                    .divide(BigDecimal.valueOf(compraRegistrada.getParcelas()), 2, RoundingMode.HALF_UP);

            for (int i = 1; i <= compraRegistrada.getParcelas(); i++) {
                int ultimoMes = compraRegistrada.getMesInicioCobranca() + i - 1;
                int ultimoAno = compraRegistrada.getAnoInicioCobranca() + (ultimoMes - 1) / 12;
                ultimoMes %= 12;
                ultimoMes = (ultimoMes == 0) ? 12 : ultimoMes;

                salvarParcela(compraRegistrada, item, valorParceladoPorResponsavel, i, ultimoMes, ultimoAno);
            }
        });
    }

    private void salvarParcela(Compra compraRegistrada, ResponsavelCompra item, BigDecimal valorParceladoPorResponsavel, int i, int ultimoMes, int ultimoAno) {
        Parcelas parcela = Parcelas.builder()
                .compraId(compraRegistrada.getId())
                .mesReferencia(ultimoMes)
                .anoReferencia(ultimoAno)
                .parcelaAtual(i)
                .responsavel(item.getId())
                .responsavelNome(item.getResponsavel())
                .valorParcelado(valorParceladoPorResponsavel.doubleValue())
                .build();
        parcelasService.salvar(parcela);
    }

    @Override
    public Compra procurarPorId(String id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(MSG_NAO_ENCONTRADO, id)));
    }

    @Override
    public void deletarPorId(String id) {
        Compra compra = procurarPorId(id);
        compraRepository.delete(compra);

        deletarParcelas(id);
    }

    @Override
    public Compra atualizar(CompraRequest request, String id) {
        List<ResponsavelCompra> responsaveis = request.getResponsaveis()
                .stream()
                .map(item -> modelMapper.map(item, ResponsavelCompra.class))
                .collect(Collectors.toList());

        Compra compraEditada = Compra.builder()
                .responsaveis(responsaveis)
                .banco(request.getBanco())
                .bancoNome(request.getBancoNome())
                .fornecedor(request.getFornecedor())
                .fornecedorNome(request.getFornecedorNome())
                .descricao(request.getDescricao())
                .dataCompra(request.getDataCompra())
                .subgrupo(request.getSubgrupo())
                .subgrupoNome(request.getSubgrupoNome())
                .parcelas(request.getParcelas())
                .valor(request.getValor())
                .mesInicioCobranca(request.getMesInicioCobranca())
                .anoInicioCobranca(request.getAnoInicioCobranca())
                .aliquotaImposta(request.getAliquotaImposta())
                .tipoCompra(request.getTipoCompra())
                .build();

        compraEditada.setDataAlteracao(LocalDateTime.now());
        compraEditada.setId(id);

        Compra compra = compraRepository.save(compraEditada);

        this.deletarParcelas(id);

        this.confeccionarParcelas(compra);

        return compra;
    }

    private void deletarParcelas(String id) {
        List<Parcelas> parcelas = parcelasService.findByParcelasPorCompra(id);
        parcelas.stream().forEach(item -> {
            parcelasService.deletarPorId(item.getId());
        });
    }
}
