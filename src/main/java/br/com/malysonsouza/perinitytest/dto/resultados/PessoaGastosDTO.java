package br.com.malysonsouza.perinitytest.dto.resultados;

public class PessoaGastosDTO {
    
    private Long id;

    private String nome;

    private double media_duracao;

    public PessoaGastosDTO(long id, String nome, Double media_duracao) {
        this.id = id;
        this.nome = nome;
        this.media_duracao = media_duracao == null ? 0 : media_duracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMedia_duracao() {
        return media_duracao;
    }

    public void setMedia_duracao(double media_duracao) {
        this.media_duracao = media_duracao;
    }
   

}
