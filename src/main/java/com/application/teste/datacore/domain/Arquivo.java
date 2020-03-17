package com.application.teste.datacore.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "arquivo")
public class Arquivo implements Serializable {
    private static final Long serialVersionUID  = 1L;

    @Id
    private UUID id;
    private String nome;
    private LocalDateTime dataCriacao;

    public Arquivo() {
        this.id = UUID.randomUUID();
    }

    public Arquivo(String nomeDoArquivo, LocalDateTime dataCriacao) {
        this.id = UUID.randomUUID();
        this.nome = nomeDoArquivo;
        this.dataCriacao = dataCriacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arquivo arquivo = (Arquivo) o;
        return id.equals(arquivo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
