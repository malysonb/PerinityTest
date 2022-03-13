package br.com.malysonsouza.perinitytest.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PesquisarPrazoDTO {
    
    private String nome;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataInicial;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataFinal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    
}
