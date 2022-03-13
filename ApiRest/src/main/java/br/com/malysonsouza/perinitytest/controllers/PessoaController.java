package br.com.malysonsouza.perinitytest.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaService service;

    @ApiOperation(value = "Salva uma pessoa dado o PessoaDTO")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Pessoa cadastrada com sucesso!."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a inserção."),
            @ApiResponse(code = 500, message = "Erro ao salvar pessoa.") })
    @PostMapping
    public ResponseEntity<Pessoa> addPessoa(@RequestBody @Valid PessoaDTO dto) throws Exception {
        return new ResponseEntity<>(service.salvar(dto, 0L), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Edita uma pessoa dado o id e o PessoaDTO")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Pessoa editada com sucesso!."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a edição."),
            @ApiResponse(code = 500, message = "Erro ao salvar pessoa.") })
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> alterarPessoa(@PathVariable("id") Long id, @RequestBody @Valid PessoaDTO dto)
            throws Exception {
        return new ResponseEntity<>(service.salvar(dto, id), HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta uma pessoa dado o id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Pessoa deletada com sucesso!."),
            @ApiResponse(code = 400, message = "Faltam dados para deletar."),
            @ApiResponse(code = 500, message = "Erro ao deletar pessoa.") })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePessoa(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(service.removerPessoa(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna todos as pessoas cadastrados em uma lista")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 500, message = "Erro ao processar a busca.") })
    @GetMapping
    public ResponseEntity<List<PessoaResultDTO>> getPessoas() {
        return new ResponseEntity<>(service.listarPessoas(), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca as pessoas baseado com condição de nome e data. Retorna horas gastas em projetos DATAS NO FORMATO dd-MM-yyyy")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 500, message = "Erro ao processar a busca.") })
    @GetMapping(path = "/gastos", headers = "Accept=application/json")
    public ResponseEntity<List<PessoaGastosDTO>> getBusca(
            @PathParam("dto") PesquisarPrazoDTO dto) {
        return new ResponseEntity<>(service.pesquisarPessoas(dto), HttpStatus.OK);
    }

}
