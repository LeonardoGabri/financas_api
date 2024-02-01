package com.financas.domain.repository.specifications;

import com.financas.api.filter.ResponsavelFilter;
import com.financas.domain.model.Responsavel;
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
public class ResponsavelSpecification implements Specification<Responsavel> {

    private ResponsavelFilter filtro;
    @Override
    public Predicate toPredicate(Root<Responsavel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(filtro.getNome() != null){
            String nomeFiltro = "%" + filtro.getNome().toUpperCase() + "%";
            log.debug("Filtrando por nome: {}", nomeFiltro);
            predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), nomeFiltro));
        }

        if(filtro.getPesquisar() != null){
            Predicate nome = criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), "%" + filtro.getPesquisar().toUpperCase());
            predicates.add(criteriaBuilder.and(criteriaBuilder.or(nome)));
        }

        return criteriaQuery.where(criteriaBuilder.and((predicates.toArray(new Predicate[0])))).getRestriction();
    }
}
