package br.com.malysonsouza.perinitytest.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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

    @Autowired
    TarefasService tarefasService;

    /**
     * Salvar uma pessoa no sistema.
     * @param dto dto para facilitar o cadastro de usuário.
     * @param id id para edição do usuário, Coloque 0L para salvar um novo usuário
     * @return Nova pessoa cadastrada
     * @throws Exception Lança uma exceção ao não encontrar departamento.
     */
    @Transactional
    public Pessoa salvar(PessoaDTO dto, long id) throws Exception {
        Pessoa pessoa = id == 0L ? new Pessoa() : pessoaRepo.getById(id);
        pessoa.setNome(dto.getNome());
        pessoa.setIdDepartamento(depRepo.findById(dto.getIdDepartamento())
            .orElseThrow(() -> new RegraNegocioException("Departamento não encontrado!")));
        return pessoaRepo.save(pessoa);
    }

    /**
     * Remove uma pessoa do banco de dados.
     * @param id id da pessoa que deve ser removida.
     * @return mensagem avisando status da operação.
     */
    public String removerPessoa(long id){
        try {
            Pessoa pessoa = pessoaRepo.findById(id).orElseThrow(() -> new RegraNegocioException("Pessoa não encontrada!"));
            pessoa.getTarefas().forEach(a -> tarefasService.desalocar(a));
            pessoaRepo.delete(pessoa);
        } catch (Exception e) {
            System.out.println(e);
            throw new RegraNegocioException("Operação não pode ser completada!");
        }
        return "Pessoa removida!";
    }

    /**
     * Lista todas as pessoas exibindo horas gastas
     * @return lista de pessoas.
     */
    public List<PessoaResultDTO> listarPessoas(){
        List<Pessoa> lista = pessoaRepo.findAll();
        List<PessoaResultDTO> result = new ArrayList<PessoaResultDTO>();
        lista.forEach(p -> {
            result.add(new PessoaResultDTO(p.getId(), p.getNome(), p.getIdDepartamento(), pessoaRepo.horasGastas(p.getId())));
        });
        return result;
    }

    /**
     * Pesquisa pessoas dado um periodo e nome.
     * @param dto
     * @return
     */
    public List<PessoaGastosDTO> pesquisarPessoas(PesquisarPrazoDTO dto){
        List<PessoaGastosDTO> lista = pessoaRepo.findPessoasMedia(dto.getDataInicial(), dto.getDataFinal(), dto.getNome());
        return lista;
    }

}
