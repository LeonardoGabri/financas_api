package com.financas.domain.repository;

import com.financas.domain.model.Fornecedor;
import com.financas.domain.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, String>, JpaSpecificationExecutor<Fornecedor> {
    @Query("SELECT b FROM Fornecedor b WHERE b.id = :id")
    Optional<Fornecedor> findById(@Param("id") String id);
}
