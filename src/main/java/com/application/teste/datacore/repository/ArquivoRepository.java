package com.application.teste.datacore.repository;

import com.application.teste.datacore.domain.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ArquivoRepository extends JpaRepository<Arquivo, UUID> {

    @Query(value = "select * from arquivo where id % 2 = 0", nativeQuery = true)
    List<Arquivo> findAllArquivosPares();
}
