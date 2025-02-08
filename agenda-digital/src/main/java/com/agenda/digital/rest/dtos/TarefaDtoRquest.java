package com.agenda.digital.rest.dtos;

import com.agenda.digital.enums.Status;
import com.agenda.digital.models.TarefaModel;

public record TarefaDtoRquest(String descricao,
                              Status status) {
    public TarefaModel toModel(){
        return new TarefaModel(null,this.descricao,null,this.status);
    }
}
