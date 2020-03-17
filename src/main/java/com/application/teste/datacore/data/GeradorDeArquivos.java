package com.application.teste.datacore.data;

import com.application.teste.datacore.config.ApplicationProperties;
import com.application.teste.datacore.domain.Arquivo;
import com.application.teste.datacore.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class GeradorDeArquivos {
    public static final int TOTAL_ARQUIVOS = 100;

    private List<Arquivo> arquivosList;
    private ApplicationProperties applicationProperties;
    private ArquivoService arquivoService;

    @Autowired
    public GeradorDeArquivos(ApplicationProperties applicationProperties, ArquivoService arquivoService) {
        this.arquivoService = arquivoService;
        this.arquivosList = new ArrayList<Arquivo>();
        this.applicationProperties = applicationProperties;
    }

    @PostConstruct
    public void gerandoArquivos() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= TOTAL_ARQUIVOS/2; i ++) {
                    escritor(i + ".txt");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = TOTAL_ARQUIVOS/2 + 1; i <= TOTAL_ARQUIVOS; i ++) {
                    escritor(i + ".txt");
                }
            }
        });

        t1.start();
        t2.start();

        arquivoService.persisteArquivos(arquivosList);

        listaParesNoArquivo();
        mostrarLog();
    }

    private void mostrarLog() {
        try {
            FileInputStream arquivo = new FileInputStream("log.txt");
            InputStreamReader input = new InputStreamReader(arquivo);
            BufferedReader br = new BufferedReader(input);

            String linha;
            do {
                linha = br.readLine();
                if (linha != null) {
                    System.out.println(linha);
                }
            } while (linha != null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void listaParesNoArquivo() {
        try {
            FileOutputStream arquivo = new FileOutputStream("log.txt");
            PrintWriter pr = new PrintWriter(arquivo);

            var listaPares = arquivoService.listarPares();
            listaPares.forEach(pr::println);

            pr.close();
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
