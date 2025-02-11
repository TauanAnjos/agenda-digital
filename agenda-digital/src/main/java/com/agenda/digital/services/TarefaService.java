package com.agenda.digital.services;

import com.agenda.digital.models.CompromissoModel;
import com.agenda.digital.models.TarefaModel;
import com.agenda.digital.repositories.CompromissoRepository;
import com.agenda.digital.repositories.TarefaRepository;
import com.agenda.digital.rest.dtos.TarefaDtoResponse;
import com.agenda.digital.rest.dtos.TarefaDtoRquest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private CompromissoRepository compromissoRepository;

    @Transactional
    public TarefaDtoResponse criarTarefa(Long userId, Long compromissoId, TarefaModel tarefaModel){
        CompromissoModel compromissoModel = compromissoRepository.findById(compromissoId).orElseThrow(()-> new RuntimeException("Compromisso não encontrado"));
        if (!compromissoModel.getUsuario().getId().equals(userId)){
            throw new RuntimeException("A tarefa não pode ser associada a esse compromisso. O compromisso não pertence ao usuário.!");
        }
        try{
            tarefaModel.setCompromisso(compromissoModel);
            return tarefaRepository.save(tarefaModel).toDtoResponse();
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao criar tarefa: " + e.getMessage(), e);
        }
    }

    public TarefaDtoResponse atualizarTarefa(Long userId, Long compromissoId, Long tarefaId, TarefaDtoRquest tarefaDtoRquest){
        CompromissoModel compromissoModel = compromissoRepository.findById(compromissoId).orElseThrow(() -> new RuntimeException("Compromisso não encontrado"));
        if (!compromissoModel.getUsuario().getId().equals(userId)){
            throw new RuntimeException("Essa tarefa não pode ser associada a esse compromisso. O compromisso não pertence ao usuário!");
        }
        TarefaModel tarefaModel = tarefaRepository.findById(tarefaId).orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));
        tarefaModel.atualizarCom(tarefaDtoRquest);
        return tarefaRepository.save(tarefaModel).toDtoResponse();
    }
}
