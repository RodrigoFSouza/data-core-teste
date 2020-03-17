package com.application.teste.datacore.data;

import com.application.teste.datacore.config.ApplicationProperties;
import com.application.teste.datacore.domain.Arquivo;
import com.application.teste.datacore.service.PersisteArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class GeradorDeArquivos {
    private List<Arquivo> arquivosList;
    private ApplicationProperties applicationProperties;
    private PersisteArquivoService persisteArquivoService;

    @Autowired
    public GeradorDeArquivos(ApplicationProperties applicationProperties, PersisteArquivoService persisteArquivoService) {
        this.persisteArquivoService = persisteArquivoService;
        this.arquivosList = new ArrayList<Arquivo>();
        this.applicationProperties = applicationProperties;
    }

    @PostConstruct
    public void gerandoArquivos() {
        for (int i = 1; i <= 100; i ++) {
            escritor(i + ".txt");
        }
        persisteArquivoService.persisteArquivos(arquivosList);
    }

    public void escritor(String nomeDoArquivo) {
        try {
            Path path = Paths.get(applicationProperties.getPath());
            if (Files.notExists(path)) {
                try {
                    Files.createDirectories(path);
                } catch (IOException e) {
                    System.out.println("Falha ao criar o diretório.");
                }
            }

            FileOutputStream arquivo = new FileOutputStream(path + "/" + nomeDoArquivo);
            PrintWriter pr = new PrintWriter(arquivo);

            LocalDateTime dataEHoraAtual = LocalDateTime.now();
            arquivosList.add(new Arquivo(nomeDoArquivo, dataEHoraAtual));

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataEHoraFormatada = dataEHoraAtual.format(dateTimeFormatter);
            pr.println(dataEHoraFormatada);

            pr.close();
            arquivo.close();
        } catch(Exception e) {
            System.out.println("Erro ao tentar escrever no arquivo: " +  e);
        }
    }
}
