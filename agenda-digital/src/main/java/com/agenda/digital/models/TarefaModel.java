package com.agenda.digital.models;

import com.agenda.digital.enums.Status;
import com.agenda.digital.rest.dtos.TarefaDtoResponse;
import com.agenda.digital.rest.dtos.TarefaDtoRquest;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_tarefa")
public class TarefaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "compromisso_id")
    private CompromissoModel compromisso;
    @Enumerated(EnumType.STRING)
    private Status status;

    public TarefaModel() {
    }

    public TarefaModel(Long id, String descricao, CompromissoModel compromisso, Status status) {
        this.id = id;
        this.descricao = descricao;
        this.compromisso = compromisso;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CompromissoModel getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(CompromissoModel compromisso) {
        this.compromisso = compromisso;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TarefaDtoResponse toDtoResponse(){
        return new TarefaDtoResponse(this.descricao, this.status);
    }

    public void atualizarCom(TarefaDtoRquest tarefaDtoRquest){
        this.descricao = tarefaDtoRquest.descricao();
        this.status = tarefaDtoRquest.status();
    }
}
