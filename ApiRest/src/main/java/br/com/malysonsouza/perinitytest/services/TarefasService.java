package br.com.malysonsouza.perinitytest.services;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.malysonsouza.perinitytest.dto.AtribuirTarefaDTO;
import br.com.malysonsouza.perinitytest.dto.TarefaDTO;
import br.com.malysonsouza.perinitytest.exceptions.RegraNegocioException;
import br.com.malysonsouza.perinitytest.models.Pessoa;
import br.com.malysonsouza.perinitytest.models.Tarefa;
import br.com.malysonsouza.perinitytest.repositories.DepartamentoRepository;
import br.com.malysonsouza.perinitytest.repositories.PessoaRepository;
import br.com.malysonsouza.perinitytest.repositories.TarefaRepository;

@Service
public class TarefasService {
    
    @Autowired
    DepartamentoRepository depRepo;

    @Autowired
    TarefaRepository tarefaRepo;

    @Autowired
    PessoaRepository pessoaRepo;

    /**
     * Retorna todas as tarefas (para checar)
     * @return lista de tarefas
     */
    public List<Tarefa> allTarefas(){
        return tarefaRepo.findAll();
    }

    /**
     * Salva ou edita uma tarefa
     * @param dto dto para facilidar a criação de uma tarefa.
     * @param id id para edição de tarefa. deixe 0L para criar uma nova tarefa.
     * @return Tarefa que acabou de ser salva.
     */
    public Tarefa addTarefa(TarefaDTO dto, long id){
        Tarefa tarefa = id == 0L ? new Tarefa() : tarefaRepo.findById(id)
            .orElseThrow(() -> new RegraNegocioException("Tarefa não encontrada!"));
        tarefa.loadDTO(dto);
        tarefa.setIdDepartamento(depRepo.findById(dto.getIdDepartamento())
                                .orElseThrow(() -> new RegraNegocioException("Departamento não encontrado!")));
        return tarefaRepo.save(tarefa);
    }

    /**
     * Atribui uma tarefa à uma pessoa dado o id da tarefa e id da pessoa
     * @param id identificação da tarefa.
     * @param dto dto onde irá receber o id da pessoa que terá a atividade atribuida.
     * @return Recebe de volta a tarefa para confirmar alterações.
     */
    public Tarefa atribuirTarefa(Long id, AtribuirTarefaDTO dto){
        Tarefa tarefa = tarefaRepo.findById(id).orElseThrow(() -> new RegraNegocioException("Esta tarefa de id '"+id+"' não existe!"));
        Optional<Pessoa> pessoa = dto == null ? getPessoasLivre(pessoaRepo.findByIdDepartamento(tarefa.getIdDepartamento().getId())) :
                                                pessoaRepo.findByIdAndDepartamento(tarefa.getIdDepartamento().getId(), dto.getIdPessoa());
        tarefa.setIdPessoa(pessoa.orElseThrow(() -> new RegraNegocioException("Esta pessoa não encontrada no departamento "+ tarefa.getIdDepartamento().getTitulo() +"!")));
        return tarefaRepo.save(tarefa);
    }

    /**
     * Finaliza uma tarefa
     * @param id id da tarefa
     * @return Tarefa para confirmação.
     */
    public Tarefa finalizarTarefa(Long id){
        Tarefa tarefa = tarefaRepo.findById(id).orElseThrow(() -> new RegraNegocioException("Esta tarefa de id '"+id+"' não existe!"));
        tarefa.setFinalizado(true);
        return tarefaRepo.save(tarefa);
    }

    /**
     * Retorna tarefas incompletas e sem pessoas atribuidas.
     * @return lista de tarefas
     */
    public List<Tarefa> pendentes(){
        List<Tarefa> tarefas = tarefaRepo.tarefasAntigas(PageRequest.of(0, 3));
        tarefas.sort( new Comparator<Tarefa>(){
            public int compare(Tarefa a, Tarefa b) {
                LocalDate d1 = a.getPrazo();
                LocalDate d2 = b.getPrazo();
                if(d1.isBefore(d2))
                    return -1;
                else if(d1.isEqual(d2))
                    return 0;
                else
                    return 1;
            }
        });
        return tarefas;
    }

    /**
     * Retorna pessoas disponiveis para atribuição automatica.
     * @param pessoas lista de pessoas.
     * @return lista de pessoas livres.
     */
    public Optional<Pessoa> getPessoasLivre(List<Pessoa> pessoas){
        List<Pessoa> l_Pessoas = pessoas.stream().filter(pessoa -> {;
            for (Tarefa t : pessoa.getTarefas()) {
                if(t.getFinalizado() == false)
                    return false;
            }
            return true;
        }).collect(Collectors.toList());
        return l_Pessoas.stream().findAny();
    }

    public String deleteTarefa(long id){
        tarefaRepo.deleteById(id);
        return "Tarefa deletada!";
    }

    public void desalocar(Tarefa tarefa){
        tarefa.setIdPessoa(null);
        tarefaRepo.save(tarefa);
    }

}
