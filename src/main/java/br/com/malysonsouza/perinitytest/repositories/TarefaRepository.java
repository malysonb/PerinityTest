package br.com.malysonsouza.perinitytest.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.malysonsouza.perinitytest.models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    @Query(value = "select * FROM tarefas WHERE tarefas.id_pessoa is null " +
    "ORDER BY tarefas.prazo ASC", nativeQuery = true)
    List<Tarefa> tarefasAntigas(Pageable page);

}
