package com.financas.domain.repository.specifications;

import com.financas.api.filter.CompraFilter;
import com.financas.api.filter.ResponsavelFilter;
import com.financas.domain.model.Compra;
import com.financas.domain.model.Responsavel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class CompraSpecification implements Specification<Compra> {

    private CompraFilter filtro;

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Predicate toPredicate(Root<Compra> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(filtro.getBancoId() != null){
            predicates.add(criteriaBuilder.equal(root.get("banco"), filtro.getBancoId()));
        }

        if(filtro.getFornecedorId() != null){
            predicates.add(criteriaBuilder.equal(root.get("fornecedor"), filtro.getFornecedorId()));
        }

        if(filtro.getSubgrupoId() != null){
            predicates.add(criteriaBuilder.equal(root.get("subgrupo"), filtro.getSubgrupoId()));
        }

        if (filtro.getDataInicio() != null) {
            if (filtro.getDataFim() != null) {
                LocalDate dataInicio = LocalDate.parse(filtro.getDataInicio(), dateFormatter);
                LocalDate dataFim = LocalDate.parse(filtro.getDataFim(), dateFormatter);

                predicates.add(criteriaBuilder.between(root.get("dataCompra"), dataInicio, dataFim));
            } else {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCompra"), LocalDate.parse(filtro.getDataInicio(), dateFormatter)));
            }
        } else if (filtro.getDataFim() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCompra"), LocalDate.parse(filtro.getDataFim(), dateFormatter)));
        }

        if(filtro.getPesquisar() != null){
            Predicate banco = criteriaBuilder.equal(root.get("bancoNome"), "%" + filtro.getPesquisar());
            Predicate forn = criteriaBuilder.equal(root.get("fornecedorNome"), "%" + filtro.getPesquisar());
            Predicate subgrupo = criteriaBuilder.equal(root.get("subgrupoNome"), "%" + filtro.getPesquisar());

            predicates.add(criteriaBuilder.and(criteriaBuilder.or(banco, forn,subgrupo)));
        }

        return criteriaQuery.where(criteriaBuilder.and((predicates.toArray(new Predicate[0])))).getRestriction();
    }
}
