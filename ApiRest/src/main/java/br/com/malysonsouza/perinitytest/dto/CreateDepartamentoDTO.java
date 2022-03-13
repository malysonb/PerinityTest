package br.com.malysonsouza.perinitytest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateDepartamentoDTO {

    @NotNull(message = "Titulo não pode ficar vazio!")
    @NotBlank(message = "Titulo não pode ficar vazio!")
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
}
