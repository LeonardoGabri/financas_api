package com.financas.domain.repository.specifications;

import com.financas.api.filter.ParcelasFilter;
import com.financas.domain.model.Compra;
import com.financas.domain.model.Parcelas;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class ParcelasSpecification implements Specification<Parcelas> {

    private ParcelasFilter filtro;
    @Override
    public Predicate toPredicate(Root<Parcelas> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filtro.getAnoReferencia() != 0) {
            predicates.add(criteriaBuilder.equal(root.get("anoReferencia"), filtro.getAnoReferencia()));
        }

        if (filtro.getMesReferencia() != 0) {
            predicates.add(criteriaBuilder.equal(root.get("mesReferencia"), filtro.getMesReferencia()));
        }

        if(filtro.getResponsavelId() != null){
            predicates.add(criteriaBuilder.equal(root.get("responsavelId"), filtro.getResponsavelId()));
        }

        if(filtro.getPesquisar() != null){
            Predicate resposavel = criteriaBuilder.equal(root.get("responsavelId"), "%" + filtro.getPesquisar());
            predicates.add(criteriaBuilder.and(criteriaBuilder.or(resposavel)));
        }

        return criteriaQuery.where(criteriaBuilder.and((predicates.toArray(new Predicate[0])))).getRestriction();
    }
}
