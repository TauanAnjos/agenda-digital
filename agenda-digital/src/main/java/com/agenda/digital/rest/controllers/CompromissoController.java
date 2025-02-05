package com.agenda.digital.rest.controllers;

import com.agenda.digital.models.CompromissoModel;
import com.agenda.digital.rest.dtos.CompromissoDtoRequest;
import com.agenda.digital.rest.dtos.CompromissoDtoResponse;
import com.agenda.digital.services.CompromissoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compromisso")
public class CompromissoController extends BaseController {

    @Autowired
    private CompromissoService compromissoService;


    @Operation(
            summary = "Criar compromisso",
            description = "Endpoint para criar compromissos.",
            tags = {"Compromissos"}
    )
    @PostMapping
    public ResponseEntity<String> registrarCompromisso(HttpServletRequest request, @RequestBody CompromissoDtoRequest compromissoDtoRequest){

        compromissoService.criarCompromisso(getUserModelSession(request).getId(), compromissoDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Compromisso criado com sucesso");
    }
    @Operation(
            summary = "Listar compromissos",
            description = "Endpoint para buscar todos os compromissos.",
            tags = {"Compromissos"}
    )
    @GetMapping
    public ResponseEntity<List<CompromissoDtoResponse>> listaDeComrpomissos(HttpServletRequest request){
        Long userId = getUserModelSession(request).getId();
        return ResponseEntity.status(HttpStatus.OK).body(compromissoService.listaDeCompromissos(userId));
    }
    @Operation(
            summary = "Encontrar por ID",
            description = "Endpoint para buscar compromisso através do ID.",
            tags = {"Compromissos"}
    )
    @GetMapping("/{id}")
    public ResponseEntity<CompromissoDtoResponse> buscarCompromissoPorId(HttpServletRequest request, @PathVariable("id") Long compromissoId){
        Long userId = getUserModelSession(request).getId();
        return ResponseEntity.status(HttpStatus.OK).body(compromissoService.BuscarCompromissoPorId(userId, compromissoId));
    }
    @Operation(
            summary = "Delete por ID",
            description = "Endpoint para deletar compromisso através do ID.",
            tags = {"Compromissos"}
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComrpomissoPorId(HttpServletRequest request, @PathVariable("id") Long compromissoId){
        Long userId = getUserModelSession(request).getId();
        compromissoService.deleteCompromissoPorId(userId, compromissoId);
        return ResponseEntity.status(HttpStatus.OK).body("Compromisso deletado com sucesso.");
    }
}
