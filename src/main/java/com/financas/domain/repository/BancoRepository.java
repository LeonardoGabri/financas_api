package com.financas.domain.repository;

import com.financas.domain.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BancoRepository extends JpaRepository<Banco, String>, JpaSpecificationExecutor<Banco> {
    @Query("SELECT b FROM Banco b WHERE b.id = :id")
    Optional<Banco> findById(@Param("id") String id);
}
