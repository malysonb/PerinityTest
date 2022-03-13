package br.com.malysonsouza.perinitytest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PessoaDTO {
    
    @NotNull(message = "Nome não pode ficar vazio!")
    @NotBlank(message = "Nome não pode ficar vazio!")
    private String nome;

    @NotNull(message = "IdDepartamento não pode ficar vazio!")
    @NotBlank(message = "IdDepartamento não pode ficar vazio!")
    private long idDepartamento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

}
