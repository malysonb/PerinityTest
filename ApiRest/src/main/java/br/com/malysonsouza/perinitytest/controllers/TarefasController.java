package br.com.malysonsouza.perinitytest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.malysonsouza.perinitytest.dto.AtribuirTarefaDTO;
import br.com.malysonsouza.perinitytest.dto.TarefaDTO;
import br.com.malysonsouza.perinitytest.models.Tarefa;
import br.com.malysonsouza.perinitytest.services.TarefasService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    TarefasService service;

    @ApiOperation(value = "Retorna a lista de tarefas")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a busca."),
            @ApiResponse(code = 500, message = "Erro ao processar a busca.") })
    @GetMapping()
    public ResponseEntity<List<Tarefa>> getAll() {
        return new ResponseEntity<>(service.allTarefas(), HttpStatus.OK);
    }

    @ApiOperation(value = "Salva uma nova tarefa prazo deve ser no formato: dd-MM-yyyy")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "item cadastrado com sucesso!."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a inserção."),
            @ApiResponse(code = 500, message = "Erro ao salvar item.") })
    @PostMapping
    public ResponseEntity<Tarefa> addTarefa(
            @ApiParam(name = "TarefaDTO", value = "Prazo deve ter o formato: dd-MM-yyyy", required = true) @RequestBody @Valid TarefaDTO dto) {
        return new ResponseEntity<>(service.addTarefa(dto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atribui uma tarefa à uma pessoa")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "item editado com sucesso!."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a edição."),
            @ApiResponse(code = 500, message = "Erro ao salvar item.") })
    @PutMapping("/alocar/{id}")
    public ResponseEntity<Tarefa> atribuirTarefa(@PathVariable("id") Long id,
            @ApiParam(name = "AtribuirTarefaDTO", value = "A pessoa deve estar no mesmo departamento", required = true) @Nullable @RequestBody AtribuirTarefaDTO dto) {
        return new ResponseEntity<>(service.atribuirTarefa(id, dto), HttpStatus.OK);
    }

    @ApiOperation(value = "Finaliza uma tarefa")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "item editado com sucesso!."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a edição."),
            @ApiResponse(code = 500, message = "Erro ao salvar item.") })
    @PutMapping("/finalizar/{id}")
    public ResponseEntity<Tarefa> finalizarTarefa(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.finalizarTarefa(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna a lista de tarefas pendentes sem pessoas atribuidas")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a busca."),
            @ApiResponse(code = 500, message = "Erro ao processar a busca.") })
    @GetMapping("/pendentes")
    public ResponseEntity<List<Tarefa>> getTarefasPendentes() {
        return new ResponseEntity<>(service.pendentes(), HttpStatus.OK);
    }

}
