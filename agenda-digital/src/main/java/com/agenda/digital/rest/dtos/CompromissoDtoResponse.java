package com.agenda.digital.rest.dtos;

import com.agenda.digital.enums.Status;
import com.agenda.digital.models.CategoriaModel;
import com.agenda.digital.models.CompromissoModel;

import java.time.LocalDateTime;
import java.util.List;

public record CompromissoDtoResponse(String titulo,
                                     String descricao,
                                     LocalDateTime data_hora,
                                     UserDtoResponse userDtoResponse,
                                     CategoriaDtoResponse categoriaDtoResponse,
                                     Status status,
                                     List<TarefaDtoResponse> tarefaDtoResponseList) {
    public CompromissoModel toModel(CategoriaModel categoriaModel){
        return new CompromissoModel(null, this.titulo, this.descricao, this.data_hora, null, categoriaModel, this.status);
    }
}
