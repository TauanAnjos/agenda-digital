package com.agenda.digital.services;

import com.agenda.digital.models.CompromissoModel;
import com.agenda.digital.models.TarefaModel;
import com.agenda.digital.repositories.CompromissoRepository;
import com.agenda.digital.repositories.TarefaRepository;
import com.agenda.digital.rest.dtos.TarefaDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private CompromissoRepository compromissoRepository;

    public TarefaDtoResponse criarTarefa(Long userId, Long compromissoId, TarefaModel tarefaModel){
        CompromissoModel compromissoModel = compromissoRepository.findById(compromissoId).orElseThrow(()-> new RuntimeException("Compromisso não encontrado"));
        if (!compromissoModel.getUsuario().getId().equals(userId)){
            throw new RuntimeException("A tarefa não pode ser associada a esse compromisso. O compromisso não é do usuário!");
        }
        try{
            tarefaModel.setCompromisso(compromissoModel);
            return tarefaRepository.save(tarefaModel).toDtoResponse();
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao criar tarefa: " + e.getMessage(), e);
        }
    }
}
