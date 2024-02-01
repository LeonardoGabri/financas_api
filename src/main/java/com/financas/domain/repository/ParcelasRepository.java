package com.financas.domain.repository;

import com.financas.domain.model.Parcelas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParcelasRepository extends JpaRepository<Parcelas, String>, JpaSpecificationExecutor<Parcelas> {
    @Query("SELECT b FROM Parcelas b WHERE b.id = :id")
    Optional<Parcelas> findById(@Param("id") String id);

    @Query("SELECT b FROM Parcelas b WHERE b.compraId = :id")
    Optional<List<Parcelas>> findByParcelasPorCompra(@Param("id") String id);
}
