package br.com.malysonsouza.perinitytest.controllers;

import java.util.ArrayList;
import java.util.List;

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

import br.com.malysonsouza.perinitytest.dto.CreateDepartamentoDTO;
import br.com.malysonsouza.perinitytest.dto.resultados.DepartamentoShowDTO;
import br.com.malysonsouza.perinitytest.exceptions.RegraNegocioException;
import br.com.malysonsouza.perinitytest.models.Departamento;
import br.com.malysonsouza.perinitytest.repositories.DepartamentoRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
    
    @Autowired
    DepartamentoRepository depRepo;

    @ApiOperation(value = "Retorna todos os departamentos em uma lista e horas gastas")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Busca realizada com sucesso."),
            @ApiResponse(code = 500, message = "Erro ao processar a busca.") })
    @GetMapping
    public ResponseEntity<List<DepartamentoShowDTO>> getDepartamentos(){
        List<DepartamentoShowDTO> lista = new ArrayList<DepartamentoShowDTO>();
        List<Departamento> cadastrados = depRepo.findAll();
        cadastrados.stream().forEach(dep -> {
            DepartamentoShowDTO temp = new DepartamentoShowDTO(dep);
            lista.add(temp);
        });
        return new ResponseEntity<>(lista, HttpStatus.OK);
    } 

    @ApiOperation(value = "Salva um novo departamento")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Departamento cadastrado com sucesso!."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a inserção."),
            @ApiResponse(code = 500, message = "Erro ao salvar departamento.") })
    @PostMapping
    public ResponseEntity<Departamento> addDepartamento(@RequestBody CreateDepartamentoDTO dto){
        return new ResponseEntity<>(depRepo.save(new Departamento(dto.getTitulo())), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Edita um departamento")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Departamento editado com sucesso!."),
            @ApiResponse(code = 400, message = "Faltam dados para realizar a inserção."),
            @ApiResponse(code = 500, message = "Erro ao salvar departamento.") })
    @PutMapping("/{id}")
    public ResponseEntity<Departamento> alteraDepartamento(@PathVariable("id") Long id, @RequestBody CreateDepartamentoDTO dto){
        Departamento dep = depRepo.findById(id).orElseThrow(() -> new RegraNegocioException("Departamento não existe!"));
        dep.setTitulo(dto.getTitulo());
        return new ResponseEntity<>(depRepo.save(dep), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deleta um departamento dado o id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Departamento deletado com sucesso!."),
            @ApiResponse(code = 400, message = "Faltam dados para deletar."),
            @ApiResponse(code = 500, message = "Erro ao deletar departamento.") })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaDepartamento(@PathVariable("id") Long id){
        depRepo.deleteById(id);
        return new ResponseEntity<>("Departamento deletado!", HttpStatus.OK);
    }

}
