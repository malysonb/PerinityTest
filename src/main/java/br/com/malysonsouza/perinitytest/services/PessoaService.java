package br.com.malysonsouza.perinitytest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.malysonsouza.perinitytest.dto.PessoaDTO;
import br.com.malysonsouza.perinitytest.exceptions.RegraNegocioException;
import br.com.malysonsouza.perinitytest.models.Pessoa;
import br.com.malysonsouza.perinitytest.repositories.DepartamentoRepository;
import br.com.malysonsouza.perinitytest.repositories.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    PessoaRepository pessoaRepo;

    @Autowired
    DepartamentoRepository depRepo;

    public Pessoa salvar(PessoaDTO dto, long id) throws Exception {
        Pessoa pessoa = id == 0L ? new Pessoa() : pessoaRepo.getById(id);
        pessoa.setNome(dto.getNome());
        pessoa.setIdDepartamento(depRepo.findById(dto.getIdDepartamento())
            .orElseThrow(() -> new RegraNegocioException("Departamento não encontrado!")));
        return pessoaRepo.save(pessoa);
    }

    public String removerPessoa(long id){
        try {
            pessoaRepo.deleteById(id);
        } catch (Exception e) {
            throw new RegraNegocioException("Pessoa não encontrada!");
        }
        return "Pessoa removida!";
    }

}
