package br.com.malysonsouza.perinitytest.dto.resultados;

import br.com.malysonsouza.perinitytest.models.Departamento;

public class DepartamentoShowDTO {
    private Long id;
    private String titulo;
    private int qntPessoas;
    private int qntTarefas;

    public DepartamentoShowDTO(Departamento dep) {
        this.id = dep.getId();
        this.titulo = dep.getTitulo();
        this.qntPessoas = dep.getPessoas().size();
        this.qntTarefas = dep.getTarefas().size();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getQntPessoas() {
        return qntPessoas;
    }
    public void setQntPessoas(int qntPessoas) {
        this.qntPessoas = qntPessoas;
    }
    public int getQntTarefas() {
        return qntTarefas;
    }
    public void setQntTarefas(int qntTarefas) {
        this.qntTarefas = qntTarefas;
    }
    
}
