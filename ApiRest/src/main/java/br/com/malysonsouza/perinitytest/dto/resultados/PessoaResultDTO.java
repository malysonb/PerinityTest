package br.com.malysonsouza.perinitytest.dto.resultados;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.malysonsouza.perinitytest.models.Departamento;

public class PessoaResultDTO {
    
    private long id;

    private String nome;

    @JsonIgnoreProperties({"pessoas", "tarefas"})
    private Departamento departamento;

    private long horasGastas;

    public PessoaResultDTO() {
    }

    public PessoaResultDTO(long id, String nome, Departamento departamento, Long horasGastas) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
        this.horasGastas = horasGastas == null ? 0 : horasGastas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public long getHorasGastas() {
        return horasGastas;
    }

    public void setHorasGastas(long horasGastas) {
        this.horasGastas = horasGastas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
