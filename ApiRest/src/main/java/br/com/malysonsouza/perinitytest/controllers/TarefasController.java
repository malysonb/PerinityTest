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

@RestController
@RequestMapping("/tarefas")
public class TarefasController {
    
    @Autowired
    TarefasService service;

    @GetMapping()
    public ResponseEntity<List<Tarefa>> getAll(){
        return new ResponseEntity<>(service.allTarefas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tarefa> addTarefa(@RequestBody @Valid TarefaDTO dto){
        return new ResponseEntity<>(service.addTarefa(dto), HttpStatus.OK);
    }

    @PutMapping("/alocar/{id}")
    public ResponseEntity<Tarefa> atribuirTarefa(@PathVariable("id") Long id, @Nullable @RequestBody AtribuirTarefaDTO dto){
        return new ResponseEntity<>(service.atribuirTarefa(id, dto), HttpStatus.OK);
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<Tarefa> finalizarTarefa(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.finalizarTarefa(id), HttpStatus.OK);
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Tarefa>> getTarefasPendentes(){
        return new ResponseEntity<>(service.pendentes(), HttpStatus.OK);
    }

}
