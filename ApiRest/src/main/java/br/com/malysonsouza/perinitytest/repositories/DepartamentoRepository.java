package br.com.malysonsouza.perinitytest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.malysonsouza.perinitytest.models.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
    

}
