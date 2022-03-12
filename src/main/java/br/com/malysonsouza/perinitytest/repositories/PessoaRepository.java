package br.com.malysonsouza.perinitytest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.malysonsouza.perinitytest.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}
