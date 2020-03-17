package com.application.teste.datacore.repository;

import com.application.teste.datacore.domain.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArquivoRepository extends JpaRepository<Arquivo, UUID> {
}
