package com.agenda.digital.rest.controllers;

import com.agenda.digital.rest.dtos.TarefaDtoResponse;
import com.agenda.digital.rest.dtos.TarefaDtoRquest;
import com.agenda.digital.services.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
public class TarefaController extends BaseController{
    @Autowired
    private TarefaService tarefaService;
    @Operation(
            summary = "Adicionar Tarefa",
            description = "Endpoint para criar tarefa e associar a compromisso.",
            tags = {"Tarefas"}
    )
    @PostMapping("/{compromissoId}")
    public ResponseEntity<TarefaDtoResponse> adicionarTarefa(HttpServletRequest request, @PathVariable("compromissoId")Long compromissoId, @RequestBody TarefaDtoRquest tarefaDtoRquest){
        Long userId = getUserModelSession(request).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.criarTarefa(userId, compromissoId, tarefaDtoRquest.toModel()));
    }
}
