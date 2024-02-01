package com.financas.domain.repository;

import com.financas.domain.model.Grupo;
import com.financas.domain.model.Subgrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubgrupoRepository extends JpaRepository<Subgrupo, String>, JpaSpecificationExecutor<Subgrupo> {
    @Query("SELECT b FROM Subgrupo b WHERE b.id = :id")
    Optional<Subgrupo> findById(@Param("id") String id);
}
