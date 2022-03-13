package br.com.malysonsouza.perinitytest.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TarefaDTO {

    @NotNull(message = "Titulo não pode ficar vazio!")
    @NotBlank(message = "Titulo não pode ficar vazio!")
    private String titulo;

    @NotNull(message = "Descricao não pode ficar vazio!")
    @NotBlank(message = "Descricao não pode ficar vazio!")
    private String descricao;

    @NotNull(message = "Prazo não pode ficar vazio!")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate prazo;

    @NotNull(message = "IdDepartamento não pode ficar vazio!")
    private long idDepartamento;

    @NotNull(message = "Duracao não pode ficar vazio!")
    private int duracao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

}
