package com.agenda.digital.rest.dtos;

import com.agenda.digital.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public record CompromissoDtoResponse(String titulo,
                                     String descricao,
                                     LocalDateTime data_hora,
                                     UserDtoResponse userDtoResponse,
                                     CategoriaDtoResponse categoriaDtoResponse,
                                     Status status,
                                     List<TarefaDtoResponse> tarefaDtoResponseList) {
}
