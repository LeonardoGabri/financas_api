package com.financas.domain.repository;

import com.financas.domain.model.Banco;
import com.financas.domain.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, String>, JpaSpecificationExecutor<Responsavel> {
    @Query("SELECT b FROM Responsavel b WHERE b.id = :id")
    Optional<Responsavel> findById(@Param("id") String id);
}
