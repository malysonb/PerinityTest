package br.com.malysonsouza.perinitytest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.malysonsouza.perinitytest.dto.PesquisarPrazoDTO;
import br.com.malysonsouza.perinitytest.dto.PessoaDTO;
import br.com.malysonsouza.perinitytest.dto.resultados.PessoaGastosDTO;
import br.com.malysonsouza.perinitytest.dto.resultados.PessoaResultDTO;
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

    public List<PessoaResultDTO> listarPessoas(){
        List<Pessoa> lista = pessoaRepo.findAll();
        List<PessoaResultDTO> result = new ArrayList<PessoaResultDTO>();
        lista.forEach(p -> {
            result.add(new PessoaResultDTO(p.getId(), p.getNome(), p.getIdDepartamento(), pessoaRepo.horasGastas(p.getId())));
        });
        return result;
    }

    public List<PessoaGastosDTO> pesquisarPessoas(PesquisarPrazoDTO dto){
        List<PessoaGastosDTO> lista = pessoaRepo.findPessoasMedia(dto.getDataInicial(), dto.getDataFinal(), dto.getNome());
        return lista;
    }

}
