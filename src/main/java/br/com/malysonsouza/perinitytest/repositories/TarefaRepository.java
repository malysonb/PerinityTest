package br.com.malysonsouza.perinitytest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.malysonsouza.perinitytest.models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
}
