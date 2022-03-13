package br.com.malysonsouza.perinitytest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.malysonsouza.perinitytest.dto.PesquisarPrazoDTO;
import br.com.malysonsouza.perinitytest.dto.PessoaDTO;
import br.com.malysonsouza.perinitytest.dto.resultados.PessoaGastosDTO;
import br.com.malysonsouza.perinitytest.dto.resultados.PessoaResultDTO;
import br.com.malysonsouza.perinitytest.models.Pessoa;
import br.com.malysonsouza.perinitytest.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    
    @Autowired
    PessoaService service;

    @PostMapping
    public ResponseEntity<Pessoa> addPessoa(@RequestBody @Valid PessoaDTO dto) throws Exception {
        return new ResponseEntity<>(service.salvar(dto, 0L), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> alterarPessoa(@PathVariable("id") Long id, @RequestBody @Valid PessoaDTO dto) throws Exception {
        return new ResponseEntity<>(service.salvar(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePessoa(@PathVariable("id") Long id) throws Exception{
        return new ResponseEntity<>(service.removerPessoa(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PessoaResultDTO>> getPessoas(){
        return new ResponseEntity<>(service.listarPessoas(), HttpStatus.OK);
    }

    @GetMapping("/gastos")
    public ResponseEntity<List<PessoaGastosDTO>> getBusca(@RequestBody PesquisarPrazoDTO dto){
        return new ResponseEntity<>(service.pesquisarPessoas(dto), HttpStatus.OK);
    }

}
