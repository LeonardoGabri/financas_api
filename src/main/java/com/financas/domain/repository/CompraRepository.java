package com.financas.domain.repository;

import com.financas.domain.model.Compra;
import com.financas.domain.model.Parcelas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<Compra, String>, JpaSpecificationExecutor<Compra> {
    @Query("SELECT b FROM Compra b WHERE b.id = :id")
    Optional<Compra> findById(@Param("id") String id);
}
