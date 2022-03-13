package br.com.malysonsouza.perinitytest.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.malysonsouza.perinitytest.dto.resultados.PessoaGastosDTO;
import br.com.malysonsouza.perinitytest.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT pessoa FROM Pessoa pessoa where pessoa.id = :idPessoa and pessoa.idDepartamento.id = :idDepartamento")
    Optional<Pessoa> findByIdAndDepartamento(@Param("idDepartamento") Long idDepartamento, @Param("idPessoa") Long idPessoa);

    @Query("SELECT pessoas FROM Pessoa pessoas where pessoas.idDepartamento.id = :idDepartamento")
    List<Pessoa> findByIdDepartamento(@Param("idDepartamento") Long idDepartamento);

    @Query("SELECT SUM(tarefas.duracao) FROM Pessoa pessoas, Tarefa tarefas WHERE pessoas.id = :idPessoa and :idPessoa = tarefas.idPessoa")
    Long horasGastas(@Param("idPessoa") Long idPessoa);

    @Query("SELECT new br.com.malysonsouza.perinitytest.dto.resultados.PessoaGastosDTO(pessoas.id, pessoas.nome, AVG(f.duracao)) "+
	"FROM Pessoa pessoas, Tarefa tarefas INNER JOIN Pessoa p ON p.id = tarefas.idPessoa "+
    "INNER JOIN Tarefa f ON f.idPessoa = pessoas.id "+
	"WHERE tarefas.prazo BETWEEN :dataInicial AND :dataFinal and pessoas.nome LIKE CONCAT('%', :nome, '%') "+
    "GROUP BY pessoas.id, pessoas.nome")
    List<PessoaGastosDTO> findPessoasMedia(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal, @Param("nome") String nome);

}
