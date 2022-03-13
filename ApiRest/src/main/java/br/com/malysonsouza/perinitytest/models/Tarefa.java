package br.com.malysonsouza.perinitytest.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.malysonsouza.perinitytest.dto.TarefaDTO;

@Entity
@Table(name = "Tarefas")
public class Tarefa {
    
    @Id
    @SequenceGenerator(initialValue = 1001, name = "tarefa_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarefa_sequence")
    private long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "prazo")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate prazo;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    @JsonIgnoreProperties({"pessoas", "tarefas"})
    private Departamento idDepartamento;

    @Column(name = "duracao")
    private int duracao;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    @JsonIgnoreProperties({"idDepartamento", "tarefas"})
    private Pessoa idPessoa;

    @Column(name = "finalizado")
    private Boolean finalizado;

    /* Construtores */

    public Tarefa() {
    }

    public Tarefa(TarefaDTO dto) {
        this.titulo = dto.getTitulo();
        this.descricao = dto.getDescricao();
        this.prazo = dto.getPrazo();
        this.duracao = dto.getDuracao();
        this.finalizado = false;
    }

    // Getters 'n' setters abaixo
    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

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

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

}
