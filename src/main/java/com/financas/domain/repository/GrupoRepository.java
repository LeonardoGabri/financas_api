package com.financas.domain.repository;

import com.financas.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, String>, JpaSpecificationExecutor<Grupo> {
    @Query("SELECT b FROM Grupo b WHERE b.id = :id")
    Optional<Grupo> findById(@Param("id") String id);
}
