package com.application.teste.datacore.service;

import com.application.teste.datacore.domain.Arquivo;
import com.application.teste.datacore.repository.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArquivoService {

    private ArquivoRepository arquivoRepository;

    @Autowired
    public ArquivoService(ArquivoRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void persisteArquivos(List<Arquivo> arquivos) {
        arquivoRepository.saveAll(arquivos);
    }

    @Transactional(readOnly = true)
    public List<Arquivo> listarPares() {
        return arquivoRepository.findAllArquivosPares();
    }
}