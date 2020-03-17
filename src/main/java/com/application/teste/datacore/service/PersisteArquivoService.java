package com.application.teste.datacore.service;

import com.application.teste.datacore.domain.Arquivo;
import com.application.teste.datacore.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersisteArquivoService {

    private ArquivoRepository arquivoRepository;

    @Autowired
    public PersisteArquivoService(ArquivoRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }

    public void persisteArquivos(List<Arquivo> arquivos) {
        arquivoRepository.saveAll(arquivos);
    }
}